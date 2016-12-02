package com.nixsolutions.hadoop.facilityavro;

import cascading.flow.Flow;
import cascading.flow.FlowDef;
import cascading.flow.FlowProcess;
import cascading.flow.hadoop2.Hadoop2MR1FlowConnector;
import cascading.operation.AssertionLevel;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.property.AppProps;
import cascading.scheme.hadoop.TextDelimited;
import cascading.scheme.hadoop.TextLine;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;
import com.nixsolutions.hadoop.model.Facility;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.oozie.client.AuthOozieClient;
import org.apache.oozie.client.CoordinatorJob;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Properties;

/**
 * Java action for working with some log file in order to process it and store.
 */
public class Main {

    private static Facility facility = new Facility();
    private static DatumWriter<Facility> datumWriter = new SpecificDatumWriter<>(
            Facility.class);
    private static DataFileWriter<Facility> fileWriter = new DataFileWriter<>(
            datumWriter);
    private static Properties properties = new Properties();

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage : "
                    + "HadoopDFSFileReadWrite <inputfile><outputfile>");
            System.exit(1);
        }

        AppProps.setApplicationJarClass(properties, Main.class);
        AppProps.addApplicationTag(properties, "avroTestApplication");
        AppProps.addApplicationTag(properties, "cluster:development");
        AppProps.setApplicationName(properties, "facility");
        Hadoop2MR1FlowConnector flowConnector = new Hadoop2MR1FlowConnector(
                properties);
        // Input file
        String inputPath = args[0];
        // Output file
        String outputPath = args[1];

        Configuration config = new Configuration();
        config.addResource(new Path("/HADOOP_HOME/conf/core-site.xml"));
        config.set("fs.default.name", "hdfs://sandbox.hortonworks.com:8020");

        FileSystem fs = FileSystem.get(config);

        Path fileNamePath = new Path("" + outputPath + "/facility.avro");
        System.out.println(System.getProperty("host_properties"));
        FSDataInputStream fsin = null;
        FSDataOutputStream fsOut = null;
        Path propertyFile = new Path("hdfs://sandbox.hortonworks.com:8020/app/config/sandbox.properties");
        try {
            if (fs.exists(fileNamePath)) {
                fs.delete(fileNamePath, true);
            }
            fsin = fs.open(propertyFile);
            fsOut = fs.create(fileNamePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] b = new byte[1];
        while(fsin.read(b)!=-1){
            bo.write(b);
        }
        String properties = new String(bo.toByteArray());
        //load properties for getting host IP
        Properties prop = new Properties();
        prop.load(new StringReader(properties));
        String host = prop.getProperty("host");

        //killing coordinator
        OozieClient oozie = new AuthOozieClient("http://" + host + ":11000/oozie");
        List<CoordinatorJob> joblist = null;
        try {
            joblist = oozie.getCoordJobsInfo("status=RUNNING", 1, 100);
//    		System.setProperty("user.name", "root");
            for (int i = 0; i < joblist.size(); i++) {
                String result = joblist.get(i).getAppName();
                if (i > 0 && result.equals("HadoopTestFacilityAvro")) {
                    oozie.kill(joblist.get(i).getId());
                }
            }
        } catch (OozieClientException e1) {
            throw new RuntimeException(e1);
        }











        // create the source tap
        Tap<?, ?, ?> source = new Hfs(new TextLine(), inputPath);

        // Create a sink tap to write to the Hfs; by default, TextDelimited
        // writes all fields out
        Tap<?, ?, ?> sink = new Hfs(new TextDelimited(true, "\t"), "Tmp",
                SinkMode.REPLACE);

        // create the job definition, and run it
        FlowDef flowDef = Main.fileProcessing(source, sink, fsOut);
        Flow wcFlow = flowConnector.connect(flowDef);
        flowDef.setAssertionLevel(AssertionLevel.VALID);
        wcFlow.complete();
        fileWriter.close();
        fsOut.close();
    }

    private static FlowDef fileProcessing(final Tap<?, ?, ?> source,
                                          final Tap<?, ?, ?> sink, final OutputStream fsOut)
            throws IOException {
        fileWriter.create(facility.getSchema(), fsOut);
        Pipe pipe = new Each("split", new Fields("line"),
                new FileProcessing(new Fields("line")), Fields.REPLACE);
        return FlowDef.flowDef().addSource(pipe, source).addTail(pipe)
                .addSink(pipe, sink);
    }

    private static class FileProcessing extends BaseOperation
            implements Function {

        private FileProcessing(final Fields fieldDeclaration)
                throws IOException {
            super(1, fieldDeclaration);
        }

        @Override
        public void operate(final FlowProcess flowProcess,
                            final FunctionCall functionCall) {
            TupleEntry argument = functionCall.getArguments();
            String line = lineProcessing(argument.getString(0));

            if (line.length() > 0) {
                Tuple result = new Tuple();
                result.add(line);
                functionCall.getOutputCollector().add(result);
            }
        }

        private String lineProcessing(final String text) {
            try {
                if (!text.contains("TruvenFacilityName")) {
                    String[] splitString = text.split("\\|");
                    for (int i = 0; i < splitString.length - 4; i++) {
                        facility.setTruvenFacilityName(splitString[i]);
                        facility.setAcoId(splitString[i + 1]);
                        facility.setTruvenFacilityId(splitString[i + 2]);
                        facility.setCDAFacilityId(splitString[i + 3]);
                        facility.setCdaClientCode(splitString[i + 4]);
                        fileWriter.append(facility);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return text;
        }
    }
}
