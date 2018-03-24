package com.thedionisio.util.mongo;


import org.bson.types.ObjectId;

/**
 * Created by jonathan on 3/21/17.
 */
public class TreatMongoId {

    public String toString(Object id){
        try
        {
            return id.toString().replace("{$oid=","").replace("}","");
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public Object toID(String id){
        try
        {
            ObjectId.isValid(id);
            new ObjectId(id);
            return  ObjectId.isValid(id);
        }
        catch (Exception e)
        {
            return "error";
        }
    }

}
