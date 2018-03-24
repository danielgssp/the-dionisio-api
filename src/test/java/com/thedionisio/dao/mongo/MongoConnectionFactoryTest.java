package com.thedionisio.dao.mongo;

import com.mongodb.Block;
import com.thedionisio.dao.mongoDB.MongoConnection;
import com.thedionisio.dao.mongoDB.MongoConnectionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan on 3/8/17.
 */
public class MongoConnectionFactoryTest {

    @Test
    public void collectionNumberTest(){
        MongoConnectionFactory mongoConnectionFactory = new MongoConnectionFactory();
        MongoConnection mongoConnection = mongoConnectionFactory.getDevConetion();
        List<String> collections = new ArrayList<>();
        mongoConnection.database.listCollectionNames().forEach((Block<? super String>) collection ->{
           collections.add(collection);
        });
        mongoConnection.client.close();



    }

    @Test
    public void collectionNameTest(){

        MongoConnectionFactory mongoConnectionFactory = new MongoConnectionFactory();
        MongoConnection mongoConnection = mongoConnectionFactory.getConetion();
        List<String> collections = new ArrayList<>();
        mongoConnection.database.listCollectionNames().forEach((Block<? super String>) collection ->{
            collections.add(collection);
        });
        mongoConnection.client.close();

       Boolean [] collectionsNames = new Boolean[collections.size()];

        for (String c: collections)
        {
            if(c.equals("person")){
                collectionsNames[0]=true;
            }
            if(c.equals("company")){
                collectionsNames[1]=true;
            }
            if(c.equals("event")){
                collectionsNames[2]=true;
            }
        }



        for (int i = 0; i<3; i++)
        {
            assertEquals(true, collectionsNames[i]);
        }

    }
}
