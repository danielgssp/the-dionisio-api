package com.thedionisio.util;

import com.thedionisio.dao.SimpleCrudRepository;
import com.thedionisio.model.dto.Person;
import com.thedionisio.services.security.Security;
import org.bson.Document;
import org.springframework.http.ResponseEntity;

/**
 * Created by jonathan on 3/27/17.
 */
public class CrudHelpTest {

    public Boolean creatPerson() {
        try {
            Object objectResponse;
            Person person = new Person();
            person.name = "Alan Turing";
            person.email = "alan@turing.com";
            person.password = Security.encryption.generateHash("turing");
            person.isActive = true;
            objectResponse = new SimpleCrudRepository().create("person", person);
            ResponseEntity<Object> responseCreate = (ResponseEntity<Object>) objectResponse;
            Boolean isCreate = Boolean.parseBoolean(responseCreate.getBody().toString());
            return isCreate;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deletePerson() {
        Document where = new Document();
        where.put("email", "alan@turing.com");
        Object objectResponse = new SimpleCrudRepository().remove("person", where);
        ResponseEntity<Object> responseDelete = (ResponseEntity<Object>) objectResponse;
        Boolean isDelete = Boolean.parseBoolean(responseDelete.getBody().toString());
        return isDelete;
    }

}
