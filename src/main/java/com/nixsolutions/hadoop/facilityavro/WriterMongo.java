package com.nixsolutions.hadoop.facilityavro;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class WriterMongo {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage : "
                    + "HadoopDFSFileRead <inputfile>");
            System.exit(1);
        }
        // Input file
        String inputPath = args[0]+ "/facility.avro";
        String jsonData = Deserializer.getJsonFromAvro(inputPath);
        try {
            /**** Connect to MongoDB ****/
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            // Now connect to your databases
            MongoDatabase db = mongoClient.getDatabase("goodJson");

            System.out.println("db.getName() - " + db.getName());

            /**** Get collection / table from 'goodJson' ****/
            // if collection doesn't exists, MongoDB will create it for you
            MongoCollection collection = db.getCollection("goodJsonColl");

            //JSON parse example
            System.out.println("JSON parse example...");

            DBObject dbObject = (DBObject)JSON.parse(jsonData);
            collection.insertOne(dbObject);

            } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
