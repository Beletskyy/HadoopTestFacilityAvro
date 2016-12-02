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
        String inputPath = args[0]+ "/facility.avro";
        List<String> jsonDataList = Deserializer.getJsonFromAvro(inputPath);
        try {
            /**** Connect to MongoDB ****/
            MongoClient mongoClient = new MongoClient(
                    new MongoClientURI("mongodb://localhost:27017"));
            /**** Now connect to databases ****/
            DB db = mongoClient.getDB("facilityDb");
            /**** Get collection / table from 'goodJson' ****/
            DBCollection collection = db.getCollection("facilityDbColl");
          //  DBCursor cursorDocJSON = collection.find();
            /**** Remove old data ****/
            collection.remove(new BasicDBObject());
            /**** Insert new data ****/
            for (String entity: jsonDataList) {
                DBObject dbObject = (DBObject)JSON.parse(entity);
                collection.insert(dbObject);
            }
            } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
