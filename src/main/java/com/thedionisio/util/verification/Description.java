package com.thedionisio.util.verification;

/**
 * Created by jonathan on 3/3/17.
 */
public interface Description {
     String PROBLEMS_DATABASE = "problems in the database";
     String NOT_FOUND_INFO ="required information not declared or information not valid";
     String NOT_VALID_INFO ="information not valid";
     String REGISTER_EXISTED = "register already exists";
     String REGISTER_NOT_FOUND = "register not found";
     String REGISTER_DELETED = "register deleted successfully";
     String REGISTER_CREATED = "register created successfully";
     String REGISTER_UPDATED = "register updated successfully";
     String REQUEST_NOT_AUTHORIZED ="request not authorized";
     String BAD_CREDENTIALS ="validation of credentials was not possible";
     String CHECK_MONGO_CONNECTION = "check your mongoDB connection";
     String REQUIRED_ID ="field <_id> is required";
     String REQUIRED_FIELDS ="required fields : ";
     String IMMUTABLE_FIELDS ="immutable fields : ";
     String FIELD_EXISTED = " > already registered in the system";
     String NOT_REGISTERED = "no register were found that satisfies the condition";
     String REFERENCE_ID = "reference _id = ";
     String PASSWORD_SHADOW = "you shall not pass";

}
