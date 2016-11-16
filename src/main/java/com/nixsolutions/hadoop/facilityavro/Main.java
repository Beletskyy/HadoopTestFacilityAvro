package com.nixsolutions.hadoop.facilityavro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import com.nixsolutions.hadoop.model.Facility;

import cascading.flow.FlowDef;
import cascading.flow.FlowProcess;
import cascading.flow.local.LocalFlowConnector;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.scheme.local.TextDelimited;
import cascading.scheme.local.TextLine;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.local.FileTap;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

/**
 * Java action for working with some log file in order to process it and store.
 *
 */
public class Main {
    private static final File outputFile = new File("bin/avro/facility.avro");

    static Facility facility = new Facility();
    static DatumWriter<Facility> datumWriter = new SpecificDatumWriter<Facility>(
            Facility.class);
    static DataFileWriter<Facility> fileWriter = new DataFileWriter<Facility>(
            datumWriter);

    public static void main(String[] args) throws IOException {

        String sourcePath = "src/main/resources/mapping";
        Tap<?, ?, ?> source = new FileTap(new TextLine(), sourcePath);

        // actual output of the job
        String sinkPath = "target/mapping.txt";
        Tap<?, ?, ?> sink = new FileTap(new TextDelimited(true, "\t"), sinkPath,
                SinkMode.REPLACE);

        if (outputFile.exists()) {
            outputFile.delete();
        }

        new File("bin/avro").mkdir();
        // create the job definition, and run it
        FlowDef flowDef = Main.fileProcessing(source, sink);
        new LocalFlowConnector().connect(flowDef).complete();
        fileWriter.close();
    }

    public static FlowDef fileProcessing(Tap<?, ?, ?> source, Tap<?, ?, ?> sink)
            throws IOException {

        // // Declare the field names used to parse out of the log file
        // Fields apacheFields = new Fields("TruvenFacilityName", "AcoId",
        // "TruvenFacilityId", "CDAFacilityId", "CdaClientCode");
        //
        // // Define the regular expression used to parse the log file
        // String apacheRegex = "^(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)$";
        //
        // // Declare the groups from the above regex. Each group will be given
        // a
        // // field name from 'apacheFields'
        // int[] allGroups = { 1, 2, 3, 4, 5 };
        //
        // // Create the parser
        // RegexParser parser = new RegexParser(apacheFields, apacheRegex,
        // allGroups);
        // // using Each for filtering values with regex
        // Pipe processPipe = new Each("processPipe", new Fields("line"),
        // parser,
        // Fields.RESULTS);
        //
        // Filter filter = new RegexFilter("[^TruvenFacilityName]");
        // processPipe = new Each(processPipe, new Fields("TruvenFacilityName"),
        // filter);
        fileWriter.create(facility.getSchema(), outputFile);
        Pipe pipe = new Each("split", new Fields("line"),
                new FileProcessing(new Fields("line")), Fields.SWAP);

        return FlowDef.flowDef()//
                .addSource(pipe, source) //
                .addTail(pipe)//
                .addSink(pipe, sink);
    }

    public static class FileProcessing extends BaseOperation
            implements Function {

        public FileProcessing(Fields fieldDeclaration) throws IOException {
            super(1, fieldDeclaration);

        }

        @Override
        public void operate(FlowProcess flowProcess,
                FunctionCall functionCall) {
            TupleEntry argument = functionCall.getArguments();
            String line = lineProcessing(argument.getString(0));

            if (line.length() > 0) {
                Tuple result = new Tuple();
                result.add(line);
                functionCall.getOutputCollector().add(result);
            }
        }

        public String lineProcessing(String text) {
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

            }
            return text;
        }
    }
}
