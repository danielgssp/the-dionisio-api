package com.thedionisio.dao.mongoDB;

import com.mongodb.MongoClient;

/**
 * Created by jonathan on 1/23/17.
 */
public class MongoConnectionFactory {

    public MongoConnection getConetion(){

        MongoConnection mongoConnection = new MongoConnection();
        mongoConnection.client = new MongoClient("localhost",27017 );
        mongoConnection.database = mongoConnection.client.getDatabase("hades");


        return mongoConnection;

    }


    public MongoConnection getDevConetion(){

        MongoConnection mongoConnection = new MongoConnection();
        mongoConnection.client = new MongoClient("localhost",27017 );
        mongoConnection.database = mongoConnection.client.getDatabase("test");


        return mongoConnection;

    }

}
