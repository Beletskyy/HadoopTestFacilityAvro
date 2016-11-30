package com.nixsolutions.hadoop.facilityavro;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.*;
import com.mongodb.util.JSON;

public class WriterMongo {
    public static void main(String[] args) {
        try {
            /**** Connect to MongoDB ****/
            MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://10.10.21.184:27017"));
            //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            // Now connect to your databases
            DB db = mongo.getDB("yourdb");
            System.out.println("db.getName() - " + db.getName());

            /**** Get collection / table from 'testdb' ****/
            // if collection doesn't exists, MongoDB will create it for you
            DBCollection collection = db.getCollection("dummyColl");
            System.out.println("collection " + collection.getName());
            System.out.println("collection FN" + collection.getFullName());
/*
            // 1. BasicDBObject example
            System.out.println("BasicDBObject example...");
            BasicDBObject document = new BasicDBObject();
            document.put("database", "mkyongDB");
            document.put("table", "hosting");

            BasicDBObject documentDetail = new BasicDBObject();
            documentDetail.put("records", 99);
            documentDetail.put("index", "vps_index1");
            documentDetail.put("active", "true");
            document.put("detail", documentDetail);

            collection.insert(document);

            DBCursor cursorDoc = collection.find();
            while (cursorDoc.hasNext()) {
                System.out.println(cursorDoc.next());
            }

            collection.remove(new BasicDBObject());

            // 2. BasicDBObjectBuilder example
            System.out.println("BasicDBObjectBuilder example...");
            BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
                    .add("database", "mkyongDB")
                    .add("table", "hosting");

            BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
                    .add("records", "99")
                    .add("index", "vps_index1")
                    .add("active", "true");

            documentBuilder.add("detail", documentBuilderDetail.get());

            collection.insert(documentBuilder.get());

            DBCursor cursorDocBuilder = collection.find();
            while (cursorDocBuilder.hasNext()) {
                System.out.println(cursorDocBuilder.next());
            }

            collection.remove(new BasicDBObject());

            // 3. Map example
            System.out.println("Map example...");
            Map<String, Object> documentMap = new HashMap<String, Object>();
            documentMap.put("database", "mkyongDB");
            documentMap.put("table", "hosting");

            Map<String, Object> documentMapDetail = new HashMap<String, Object>();
            documentMapDetail.put("records", "99");
            documentMapDetail.put("index", "vps_index1");
            documentMapDetail.put("active", "true");

            documentMap.put("detail", documentMapDetail);

            collection.insert(new BasicDBObject(documentMap));

            DBCursor cursorDocMap = collection.find();
            while (cursorDocMap.hasNext()) {
                System.out.println(cursorDocMap.next());
            }

            collection.remove(new BasicDBObject());
*/

            // 4. JSON parse example
            System.out.println("JSON parse example...");

            String json = "{'database' : 'mkyongDB','table' : 'hosting'," +
                    "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";

            DBObject dbObject = (DBObject)JSON.parse(json);

            collection.save(dbObject);

            DBCursor cursorDocJSON = collection.find();
            while (cursorDocJSON.hasNext()) {
                System.out.println("IN while");
                System.out.println(cursorDocJSON.next());
            }

            //collection.remove(new BasicDBObject());

            } /*catch (UnknownHostException e) {
                e.printStackTrace();
            }*/
        catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
