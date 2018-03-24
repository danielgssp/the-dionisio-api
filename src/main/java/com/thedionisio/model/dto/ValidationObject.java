package com.thedionisio.model.dto;

import org.springframework.http.HttpStatus;

/**
 * Created by jonathan on 3/2/17.
 */
public class ValidationObject {

    public HttpStatus status;
    public String description;
    public Object additional;
}
