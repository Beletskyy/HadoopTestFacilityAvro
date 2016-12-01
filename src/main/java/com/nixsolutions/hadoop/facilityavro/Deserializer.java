package com.nixsolutions.hadoop.facilityavro;


import com.nixsolutions.hadoop.model.Facility;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.FileReader;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.mapred.FsInput;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.tool.DataFileReadTool;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Deserializer {
/*    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder("{");
        //DeSerializing the objects
        DatumReader<Facility> facilityDatumReader = new SpecificDatumReader<Facility>(Facility.class);

        //Instantiating DataFileReader
        DataFileReader<Facility> dataFileReader = new DataFileReader<Facility>(new
                File("src/main/resources/facility.avro"), facilityDatumReader);
        Facility model = null;

        while(dataFileReader.hasNext()){
            model = dataFileReader.next(model);
//            System.out.println(model);
            result.append(model);
        }
        result.append("}");
        System.out.println(result);
    }*/

    public static String getJsonFromAvro(String pathAvroFile) {
        System.out.println("in method");
        System.out.println(pathAvroFile);
        Facility model = null;
        StringBuilder result = new StringBuilder("{");

        Configuration config = new Configuration();
        config.addResource(new Path("/HADOOP_HOME/conf/core-site.xml"));
        config.set("fs.default.name", "hdfs://sandbox.hortonworks.com:8020");
        try {
            FileSystem fs = FileSystem.get(config);
            Path path = new Path(pathAvroFile);
            FSDataInputStream inputStream = fs.open(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            SeekableInput in = new FsInput(path, config);
            DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>();
            FileReader<GenericRecord> fileReader = DataFileReader.openReader(in, reader);

            for (GenericRecord datum : fileReader) {
                System.out.println("value = " + datum);
            }

            fileReader.close();
            /*
//            SeekableInput input = new SeekableByteArrayInput(IOUtils.toByteArray(br));

            //DeSerializing the objects
            DatumReader<Facility> facilityDatumReader = new SpecificDatumReader<Facility>(Facility.class);
            //Instantiating DataFileReader
            DataFileReader<Facility> dataFileReader = null;

            dataFileReader = new DataFileReader<Facility>(input, facilityDatumReader);
            while(dataFileReader.hasNext()){
                model = dataFileReader.next(model);
//            System.out.println(model);
                result.append(model);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.append("}");
        System.out.println("result - " + result);
        return result.toString();
    }

    /*public static void main(String[] args) throws IOException {
        //Instantiating the Schema.Parser class.
        Schema schema = new Schema.Parser().parse(new File("src/main/avro/facility.avsc"));
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader =
                new DataFileReader<GenericRecord>(new File("src/main/resources/facility.avro")
                                                    , datumReader);
        GenericRecord emp = null;

        while (dataFileReader.hasNext()) {
            emp = dataFileReader.next(emp);
            System.out.println(emp);
        }
    }*/

/*    public static String getJsonFromAvro(String pathAvscFile, String pathAvroFile) {
        StringBuilder result = new StringBuilder("");
        //Instantiating the Schema.Parser class.
        Schema schema = null;
        try {
            schema = new Schema.Parser().parse(new File(pathAvscFile));
            DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
            DataFileReader<GenericRecord> dataFileReader =
                    new DataFileReader<GenericRecord>(new File(pathAvroFile)
                            , datumReader);
            GenericRecord emp = null;

            while (dataFileReader.hasNext()) {
                emp = dataFileReader.next(emp);
                result.append(emp);
//            System.out.println(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }*/

}
