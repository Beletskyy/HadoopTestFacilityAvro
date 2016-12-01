package com.nixsolutions.hadoop.facilityavro;

import java.util.HashMap;
import java.util.Map;

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
        String inputPath = args[0];
        String jsonData = Deserializer.getJsonFromAvro(inputPath);

        try {
            /**** Connect to MongoDB ****/
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            // Now connect to your databases
            DB db = mongoClient.getDB("facilityDb");
            System.out.println("db.getName() - " + db.getName());

            /**** Get collection / table from 'facilityDb' ****/
            // if collection doesn't exists, MongoDB will create it for you
            DBCollection collection = db.getCollection("facilityCollDb");

            //JSON parse example
            System.out.println("JSON parse example...");

//            String json = "{'hosting' : 'hostA', 'type' : 'vps', 'clients' : 1000}, { 'hosting' : 'hostB', 'type' : 'dedicated server', 'clients' : 100}, { 'hosting' : 'hostC', 'type' : 'vps', 'clients' : 900}";
/*
            String json = "{'database' : 'mkyongDB','table' : 'hosting'," +
                    "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";

*/

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
