package com.nixsolutions.hadoop.facilityavro;


import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.FileReader;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Deserializer {
    static List<String> getJsonFromAvro(String pathAvroFile) {
        System.out.println("in method");
        System.out.println(pathAvroFile);
        List<String> resultList = new ArrayList<>();


        Configuration config = new Configuration();
        config.addResource(new Path("/HADOOP_HOME/conf/core-site.xml"));
        config.set("fs.default.name", "hdfs://sandbox.hortonworks.com:8020");
        try {
            FileSystem fs = FileSystem.get(config);
            Path path = new Path(pathAvroFile);
            FSDataInputStream inputStream = fs.open(path);
            SeekableInput in = new FsInput(path, config);
            DatumReader<GenericRecord> reader
                    = new GenericDatumReader<GenericRecord>();
            FileReader<GenericRecord> fileReader
                    = DataFileReader.openReader(in, reader);

            for (GenericRecord datum : fileReader) {
                resultList.add(datum.toString());
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resultList.size());
        System.out.println(resultList);
        return resultList;
    }
}
