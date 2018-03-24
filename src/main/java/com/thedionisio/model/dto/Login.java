package com.thedionisio.model.dto;


/**
 * Created by jonathan on 5/20/17.
 */
public class Login {
    @Override
    public String toString() {
        return "Login{" +
                "token='" + token + '\'' +
                ", entity=" + entity +
                '}';
    }

    public String token;
    public Object entity;
}
