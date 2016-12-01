package com.nixsolutions.hadoop.facilityavro;

import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.List;

public class WriterMongo {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage : "
                    + "HadoopDFSFileRead <inputfile>");
            System.exit(1);
        }
        // Input file
        String inputPath = args[0]+ "/facility.avro";
        List<String> jsonDataList = Deserializer.getJsonFromAvro(inputPath);
        try {
            /**** Connect to MongoDB ****/
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            // Now connect to your databases
            DB db = mongoClient.getDB("goodJson");

            /**** Get collection / table from 'goodJson' ****/
            // if collection doesn't exists, MongoDB will create it for you
            DBCollection collection = db.getCollection("goodJsonColl");
            DBCursor cursorDocJSON = collection.find();
            collection.remove(new BasicDBObject());

            for (String entity: jsonDataList) {
                DBObject dbObject = (DBObject)JSON.parse(entity);
                collection.insert(dbObject);
            }
/*
            DBCursor cursorDocJSON = collection.find();
            while (cursorDocJSON.hasNext()) {
                System.out.println(cursorDocJSON.next());
            }
//            collection.remove(new BasicDBObject());
*/

            } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
