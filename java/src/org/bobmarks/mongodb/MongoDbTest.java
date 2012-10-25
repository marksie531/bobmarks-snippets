package org.bobmarks.mongodb;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Class to illustrate using the mongo database
 */
public class MongoDbTest {

    /**
     * Constructor
     */
    public MongoDbTest() {
        try {
            Mongo m = new Mongo("localhost", 27017);

            DB db = m.getDB("test");

            System.out.println("1) Display connections");
            Set<String> colls = db.getCollectionNames();
            for (String s : colls) {
                System.out.println("\t" + s);
            }

            // Grab collection
            DBCollection coll = db.getCollection("test");

            System.out.println("\n2) Drop items in collection");
            coll.drop();

            System.out.println("\n3) Add some test data");
            coll.insert(new BasicDBObject("a", 1));
            coll.insert(new BasicDBObject("a", 2));
            coll.insert(new BasicDBObject("a", 3));
            coll.insert(new BasicDBObject("a", 4));
            coll.insert(new BasicDBObject("a", 5));

            System.out.println("\n4) Display count of collection [ test ]");
            System.out.println("\t" + coll.getCount());

            System.out.println("\n5) Display all entries");
            findAndDisplay(coll, null);

            System.out.println("\n6) Retrieve a single item from collection");
            BasicDBObject query = new BasicDBObject();
            query.put("a", 2);
            findAndDisplay(coll, query);

            System.out.println("\n7) Add a record");
            BasicDBObject cityDoc = new BasicDBObject();
            cityDoc.put("city", "Belfast");
            cityDoc.put("areas", new String[] { "South", "North", "West",
                    "East" });
            coll.insert(cityDoc);
            findAndDisplay(coll, null);

            System.out.println("\n6) Modify record");
            query = new BasicDBObject("city", "Belfast");
            DBObject modify = coll.findOne(query);
            modify.put("city", "London");
            coll.update(query, modify);
            findAndDisplay(coll, new BasicDBObject("city", "London"));

            System.out.println("\n7) Delete added record");
            query = new BasicDBObject();
            query.put("city", "London");
            coll.remove(query);
            findAndDisplay(coll, null);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void findAndDisplay(DBCollection coll, BasicDBObject query) {
        DBCursor cursor = null;
        try {
            cursor = query != null ? coll.find(query) : coll.find();
            while (cursor.hasNext()) {
                System.out.println("\t" + cursor.next());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * Main method.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new MongoDbTest();
    }
}