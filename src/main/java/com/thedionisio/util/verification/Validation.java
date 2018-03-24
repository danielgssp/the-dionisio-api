package com.thedionisio.util.verification;

/**
 * Created by jonathan on 3/28/17.
 */
public interface Validation {

    RequestValidation resquest = new  RequestValidation();
    DocumentsValidation document = new DocumentsValidation();
    FieldValidation field = new FieldValidation();
}
