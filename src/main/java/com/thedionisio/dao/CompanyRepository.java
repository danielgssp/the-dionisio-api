package com.thedionisio.dao;

import com.thedionisio.dao.mongoDB.MongoCrud;
import com.thedionisio.model.dto.Company;
import org.bson.Document;

import java.util.List;

/**
 * Created by jonathan on 4/9/17.
 */
public class CompanyRepository extends SimpleCrudRepository {

    private MongoCrud mongoCrud = new MongoCrud();
    private Document query;
    private final String collection ="company";

    public Object findByEmail(String email)
    {
        query = new Document();
        query.put("email",email);
        return mongoCrud.find(collection,new Company(),query,new Document(),0);
    }

    public Boolean isActive(Object id)
    {
        try
        {
            List<Company> companies = (List<Company>) super.findOne(collection,id,new Company());

            return companies.get(0).isActive;
        }
        catch (Exception e)
        {
            return false;
        }
    }


}
