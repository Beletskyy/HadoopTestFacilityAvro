package com.nixsolutions.hadoop.facilityavro;


import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.FileReader;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

class Deserializer extends PropertyHolder {
    static List<Document> getJsonFromAvro(String pathAvroFile) {
        List<Document> resultList = new ArrayList<>();
        try {
            Path path = new Path(pathAvroFile);
            SeekableInput in = new FsInput(path, config);
            DatumReader<GenericRecord> reader
                    = new GenericDatumReader<GenericRecord>();
            FileReader<GenericRecord> fileReader
                    = DataFileReader.openReader(in, reader);
            for (GenericRecord datum : fileReader) {
                String str = datum.toString();
                Document document = Document.parse(str);
                resultList.add(document);
            }
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }
}
