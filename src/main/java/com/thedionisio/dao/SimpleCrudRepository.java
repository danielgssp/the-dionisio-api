package com.thedionisio.dao;

import com.thedionisio.dao.mongoDB.MongoCrud;
import com.thedionisio.util.verification.RequestValidation;
import com.thedionisio.util.verification.Validation;
import org.bson.Document;
import org.springframework.http.ResponseEntity;

/**
 * Created by jonathan on 3/3/17.
 */
public class SimpleCrudRepository {
    RequestValidation validation = new RequestValidation();


    private MongoCrud simpleCrud = new MongoCrud();

    public ResponseEntity create(String collection, Object object) {

        if (simpleCrud.create(collection, object)) {
            return validation.THAT_OK(true);
        }
        return validation.NOT_DATA_BASE();
    }

    public Object find(String collection, Object object, Document query, Document sort, Integer limit) {

        Object result = simpleCrud.find(collection, object, query, sort, limit);

        if (result != null) {
            return result;
        }
        return validation.NOT_DATA_BASE();
    }


    public Object findOne(String collection, Object id, Object objectBase){
        try
        {
            if(id==null) {return Validation.resquest.NOT_CONTAINS_ID();}

            return findOneBlock(collection,id, objectBase);

        }
        catch (Exception e)
        {
            return Validation.resquest.NOT_DATA_BASE();
        }
    }


    private Object findOneBlock(String collection, Object id, Object object) {

        Object result = simpleCrud.findOne(collection, id, object);

        if (result != null) {
            return result;
        }
        return validation.ITEM_NOT_FOUND(id);
    }

    public Object update(Object updateObject, Object id, String collection) {

        try
        {
            if (id==null) return Validation.resquest.NOT_CONTAINS_ID();

            Object objectResponse = updateBlock(collection, id.toString(), updateObject);
            try
            {
                if ((Boolean) objectResponse)
                {
                    return this.findOne(collection,id.toString(),updateObject);
                }
                return Validation.resquest.REQUEST_NOT_AUTHORIZED();
            }
            catch (Exception e)
            {
                return objectResponse;
            }
        }
        catch (Exception e) {
            return Validation.resquest.NOT_DATA_BASE();
        }
    }

    public Object updateBlock(String collection, Object id, Object object) {

        Object item = simpleCrud.update(collection, id, object);
        if (item != null && !item.equals(false)) {
            return true;
        }
        return validation.ITEM_NOT_FOUND(item);
    }

    public Object removeOne(Object id, String collection) {
        try {
            if (Validation.resquest.idValidation(id.toString())) {
                Object objectResponse = removeBlock(collection, id.toString());

                ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) objectResponse;

                try {
                    if ((Boolean) responseEntity.getBody()) {
                        return Validation.resquest.REGISTRY_DELETED(id.toString());
                    }
                } catch (Exception e) {
                    return responseEntity;
                }
            }
            return Validation.resquest.NOT_CONTAINS_ID();
        } catch (Exception e) {
            return Validation.resquest.NOT_DATA_BASE();
        }
    }

    private Object removeBlock(String collection, Object id) {

        Object result = simpleCrud.removeOne(collection, id);

        if (result != null) {
            if (Integer.valueOf(result.toString()) > 0) {
                return validation.THAT_OK(true);
            }
            return validation.ITEM_NOT_FOUND(result);
        }
        return validation.NOT_DATA_BASE();

    }

    public Object remove(String collection, Document where) {

        Object result = simpleCrud.remove(collection, where);

        if (result != null) {
            if (Integer.valueOf(result.toString()) > 0) {
                return validation.THAT_OK(true);
            }
            return validation.ITEM_NOT_FOUND(result);
        }
        return validation.NOT_DATA_BASE();

    }




}








