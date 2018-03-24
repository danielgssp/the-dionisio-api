package com.thedionisio.dao;

import com.thedionisio.dao.mongoDB.MongoCrud;
import com.thedionisio.model.dto.Person;
import org.apache.tomcat.jni.Local;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan on 3/8/17.
 */
public class SimpleCrudRepositoryTest {

    private SimpleCrudRepository simpleCrudRepository = new SimpleCrudRepository();
    private MongoCrud mongoCrud = new MongoCrud();
    private final String collection = "person";
    private Boolean isCreate;
    private Boolean isRead;
    private Boolean isUpdate;
    private Boolean isDelete;

    public SimpleCrudRepositoryTest(){
        Object objectResponse;
        Person person = new Person();
        person.name = "Alan Turing";
        person.email = "alan@turing.com";
        person.password = "turing";
        person.isActive = true;
        objectResponse = simpleCrudRepository.create(collection, person);
        ResponseEntity<Object> responseCreate = (ResponseEntity<Object>) objectResponse;
        isCreate = Boolean.parseBoolean(responseCreate.getBody().toString());

        Document where = new Document();
        where.put("email", "alan@turing.com");
        objectResponse = simpleCrudRepository.find(collection, new Person(), where, new Document(), 0);;
        List<Person> persons = (List<Person>) objectResponse;
        isRead = persons.get(0).isActive;

        person.name = "Turing";
        person._id = persons.get(0)._id.toString().replace("{$oid=", "").replace("}", "");
        simpleCrudRepository.update(person, person._id, collection);
        objectResponse = simpleCrudRepository.find(collection, new Person(), where, new Document(), 0);
        persons = (List<Person>) objectResponse;
        isUpdate = persons.get(0).name.equals("Turing");

        objectResponse = simpleCrudRepository.remove(collection, where);
        ResponseEntity<Object>responseRead = (ResponseEntity<Object>) objectResponse;
        isDelete = Boolean.parseBoolean(responseRead.getBody().toString());
    }

    @Test
    public void creteTest() {
        assertEquals(true, isCreate);
    }

    @Test
    public void readTest() {
        assertEquals(true, isRead);
    }

    @Test
    public void updateTest() {
        assertEquals(true, isUpdate);
    }

    @Test
    public void deleteTest() {
        assertEquals(true, isDelete);
    }
}