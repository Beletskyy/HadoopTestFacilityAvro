package com.nixsolutions.hadoop.facilityavro;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class WriterMongo {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage : "
                    + "HadoopDFSFileRead <inputfile>");
            System.exit(1);
        }
        String inputPath = args[0]+ "/facility.avro";
        List<Document> jsonDataList = Deserializer.getJsonFromAvro(inputPath);
        try {
            /**** Connect to MongoDB ****/
            MongoClient mongoClient = new MongoClient(
                    new MongoClientURI("mongodb://localhost:27017"));
            /**** Now connect to databases ****/
            MongoDatabase db = mongoClient.getDatabase("mongoClient");
            /**** Get collection / table from 'goodJson' ****/
            MongoCollection<Document> collection = db.getCollection("mongoClientColl");
            /**** Remove old data ****/
            collection.drop();
            /**** Insert new data ****/
            collection.insertMany(jsonDataList);
        } catch (MongoException e) {
            throw new RuntimeException(e);
        }
    }
}
