package com.thedionisio.model.dto;

import com.thedionisio.util.verification.Description;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan on 5/26/17.
 */
public class PersonTest {

    @Test
    public void createFailTest(){
        Person person = new Person();
        assertEquals(false, person.createValidation());
    }

    @Test
    public void createOkTest(){
        Person person = new Person();
        person.name = "Alan Turing";
        person.email = "alan@turing.com";
        person.password = "turing";
        person.genres = new ArrayList<>();
        person.genres.add("AbCd");
        assertEquals(true, person.createValidation());
    }

    @Test
    public void treatCreateGenresTest(){
        Person person = new Person();
        person.genres = new ArrayList<>();
        person.genres.add("AbCd");
        person = person.treatCreate();
        assertEquals("abcd",person.genres.get(0));
    }

    @Test
    public void treatCreateActiveTest(){
        Person person = new Person();
        person = person.treatCreate();
        assertEquals(true,person.isActive);
    }

    @Test
    public void treatCreatePassword(){
        Person person = new Person();
        person.password = "turing";
        person = person.treatCreate();
        assertEquals("50b93f84786c78783db8ba559d237a315d0d8e6c",person.password);
    }

    @Test
    public void treatResponse(){
        Person person = new Person();
        person.password = "turing";
        person = person.treatResponse();
        assertEquals(Description.PASSWORD_SHADOW,person.password);
    }

}
