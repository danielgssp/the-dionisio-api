package com.thedionisio.dao;

import com.thedionisio.dao.mongoDB.MongoCrud;
import com.thedionisio.model.dto.Person;
import org.bson.Document;

import java.util.List;

/**
 * Created by jonathan on 3/8/17.
 */
public class PersonRepository extends SimpleCrudRepository{

    private MongoCrud mongoCrud = new MongoCrud();
    private Document query;
    private final String collection ="person";

    public Object findByEmail(String email)
    {
        query = new Document();
        query.put("email",email);
        return mongoCrud.find(collection,new Person(),query,new Document(),0);
    }

    public Boolean isActive(Object id)
    {
        try
        {
            List<Person> people = (List<Person>) super.findOne(collection,id,new Person());

            return people.get(0).isActive;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
