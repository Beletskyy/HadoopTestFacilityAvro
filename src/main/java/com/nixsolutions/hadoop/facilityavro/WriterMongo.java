package com.nixsolutions.hadoop.facilityavro;

import com.mongodb.*;
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
        System.out.println("inputPath - " + inputPath);
        String jsonData = Deserializer.getJsonFromAvro(inputPath);
        try {
            /**** Connect to MongoDB ****/
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            // Now connect to your databases
            DB db = mongoClient.getDB("goodJson");
            System.out.println("db.getName() - " + db.getName());

            /**** Get collection / table from 'goodJson' ****/
            // if collection doesn't exists, MongoDB will create it for you
            DBCollection collection = db.getCollection("goodJsonColl");

            //JSON parse example
            System.out.println("JSON parse example...");

            DBObject dbObject = (DBObject)JSON.parse(jsonData);
            collection.insert(dbObject);

            DBCursor cursorDocJSON = collection.find();
            while (cursorDocJSON.hasNext()) {
                System.out.println(cursorDocJSON.next());
            }
//            collection.remove(new BasicDBObject());

            } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
