package com.nixsolutions.hadoop.facilityavro;


import com.nixsolutions.hadoop.model.Facility;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.*;

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
        Facility model = null;
        StringBuilder result = new StringBuilder("{");
        //DeSerializing the objects
        DatumReader<Facility> facilityDatumReader = new SpecificDatumReader<Facility>(Facility.class);
        //Instantiating DataFileReader
        DataFileReader<Facility> dataFileReader = null;
        try {
            dataFileReader = new DataFileReader<Facility>(new
                    File(pathAvroFile), facilityDatumReader);
            while(dataFileReader.hasNext()){
                model = dataFileReader.next(model);
//            System.out.println(model);
                result.append(model);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.append("}");
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

    public static String getJsonFromAvro(String pathAvscFile, String pathAvroFile) {
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
    }

}
