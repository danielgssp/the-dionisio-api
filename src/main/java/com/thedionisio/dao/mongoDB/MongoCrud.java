package com.thedionisio.dao.mongoDB;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 1/26/17.
 */


public class MongoCrud {

    MongoConnectionFactory mongoConnectionFactory = new MongoConnectionFactory();

    public Boolean create(String collection, Object item) {

        MongoConnection mongoConnection = null;
        try
        {
            mongoConnection = mongoConnectionFactory.getConetion();
            MongoCollection c = mongoConnection.database.getCollection(collection);
            c.insertOne(toDocument(item));
            mongoConnection.client.close();

            return true;
        }
        catch (Exception e)
        {
            mongoConnection.client.close();
            return false;
        }

    }


    public Object find(String collection, Object object, Document queryFind, Document querySort, Integer limint){

        List list = new ArrayList();
        MongoConnection mongoConnection = mongoConnectionFactory.getConetion();
        try
        {
            MongoCollection c = mongoConnection.database.getCollection(collection);

            FindIterable<Document> searcheResult = c.find(queryFind).sort(querySort).limit(limint);

            for (Document document : searcheResult){
                Object item = new Object();
                item = new Gson().fromJson(document.toJson(), object.getClass());
                list.add(item);
            }

        }
        catch (Exception e)
        {
            mongoConnection.client.close();
            return null;
        }

        mongoConnection.client.close();
        return list;
    }



    public Object findOne(String collection, Object id, Object object) {

        List list = new ArrayList();
        MongoConnection mongoConnection = mongoConnectionFactory.getConetion();
        try
        {

            MongoCollection c = mongoConnection.database.getCollection(collection);
            FindIterable<Document> searcheResult = c.find(getDocumentID(id));
            for (Document document : searcheResult){
                Object item = new Object();
                item = new Gson().fromJson(document.toJson(), object.getClass());
                list.add(item);
            }


        }
        catch (Exception e)
        {
            mongoConnection.client.close();
            return null;
        }

        mongoConnection.client.close();
        return list;
    }

    public Object  update(String collection, Object id, Object object){

        MongoConnection mongoOldConnection = mongoConnectionFactory.getConetion();

        try
        {
            MongoConnection mongoConnection = mongoConnectionFactory.getConetion();
            MongoCollection c = mongoConnection.database.getCollection(collection, object.getClass());
            UpdateOptions updateOptions = new UpdateOptions();
            updateOptions.upsert(false);
            UpdateResult updateResult = c.updateOne(getDocumentID(id), toDocumentToUpdate(object), updateOptions);
            mongoConnection.client.close();
            return updateResult.isModifiedCountAvailable();
        }
        catch (Exception e)
        {
          mongoOldConnection.client.close();
          return  null;
        }

    }

    public Object removeOne(String collection, Object id){
        MongoConnection mongoConnection = mongoConnectionFactory.getConetion();
        try
        {
            MongoCollection c = mongoConnection.database.getCollection(collection);
            DeleteResult deleteResult = c.deleteOne(getDocumentID(id));
            mongoConnection.client.close();
            return deleteResult.getDeletedCount();
        }
        catch (Exception e)
        {
            mongoConnection.client.close();
            return null;
        }

    }


    public Object remove(String collection, Document where){
        MongoConnection mongoConnection = mongoConnectionFactory.getConetion();
        try
        {
            MongoCollection c = mongoConnection.database.getCollection(collection);
            DeleteResult deleteResult = c.deleteOne(where);
            mongoConnection.client.close();
            return deleteResult.getDeletedCount();
        }
        catch (Exception e)
        {
            mongoConnection.client.close();
            return null;
        }

    }

    private Document getDocumentID(Object id){
        ObjectId objectId = new ObjectId(id.toString().replace("{$oid=","").replace("}",""));
        Document objectID = new Document();
        objectID.put("_id",objectId);
        return  objectID;
    }

    private Document toDocument(Object object)  {
        return Document.parse(new Gson().toJsonTree(object).toString());
    }

    private Document toDocumentToUpdate(Object object){
        Document documentToUpdate = new Document();
        Document newData = toDocument(object);
        newData.remove("_id");
        documentToUpdate.put("$set",newData);
        return documentToUpdate;
    }






}
