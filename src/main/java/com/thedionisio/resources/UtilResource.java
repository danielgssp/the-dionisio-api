package com.thedionisio.resources;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.thedionisio.dao.mongoDB.MongoConnection;
import com.thedionisio.dao.mongoDB.MongoConnectionFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jonathan on 5/11/17.
 */

@RestController
@RequestMapping(value = "/drop")
        public class UtilResource {

    @RequestMapping(value = "/{colletion:.+}",method = RequestMethod.GET)
    public String drop(@PathVariable String colletion){
       try
       {
           long deletou=0;
           MongoConnection connection = new MongoConnectionFactory().getConetion();
           MongoDatabase db = connection.database;
           MongoCollection collection = db.getCollection(colletion);
           deletou = collection.count();
           collection.drop();
           collection = db.getCollection(colletion);
           if (collection.count()==0 && deletou==1)
           return "OK. Collection " + colletion +" deletada com sucesso";
       }
       catch (Exception e)
       {
       }
        return "Não foi possivel deletar da collection [ "+ colletion + " ]";
    }

}
