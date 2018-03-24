package com.thedionisio.util.verification;

import com.thedionisio.model.dto.ValidationObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by jonathan on 2/28/17.
 */
public class RequestValidation {

    private ValidationObject validationObject = new ValidationObject();

    private ValidationObject notContainsID()
    {
        validationObject.status = HttpStatus.NOT_ACCEPTABLE;
        validationObject.description = Description.NOT_FOUND_INFO;
        validationObject.additional =Description.REQUIRED_ID;

       return validationObject;
    }
    private ValidationObject notContainsFields(String fields)
    {
        validationObject.status = HttpStatus.NOT_ACCEPTABLE;
        validationObject.description = Description.NOT_FOUND_INFO;
        validationObject.additional = Description.REQUIRED_FIELDS + fields;

        return validationObject;
    }
    private ValidationObject containsFieldsImmutable(String fields)
    {
        validationObject.status = HttpStatus.NOT_ACCEPTABLE;
        validationObject.description = Description.NOT_VALID_INFO;
        validationObject.additional = Description.IMMUTABLE_FIELDS + fields;

        return validationObject;
    }
    private ValidationObject notDataBase()
    {
        validationObject.status = HttpStatus.INSUFFICIENT_STORAGE;
        validationObject.description = Description.PROBLEMS_DATABASE;
        validationObject.additional = Description.CHECK_MONGO_CONNECTION;

        return validationObject;
    }
    private ValidationObject existing(String field)
    {
        validationObject.status = HttpStatus.NOT_ACCEPTABLE;
        validationObject.description = Description.REGISTER_EXISTED;
        validationObject.additional = field + Description.FIELD_EXISTED;

        return validationObject;
    }
    private ValidationObject notFound()
    {
        validationObject.status = HttpStatus.NOT_FOUND;
        validationObject.description = Description.REGISTER_NOT_FOUND;
        validationObject.additional = Description.NOT_REGISTERED;

        return validationObject;
    }
    private ValidationObject notAutorized()
    {
        validationObject.status = HttpStatus.UNAUTHORIZED;
        validationObject.description = Description.REQUEST_NOT_AUTHORIZED;
        validationObject.additional = Description.BAD_CREDENTIALS;

        return validationObject;
    }
    private ValidationObject registryDeleted(String id)
    {
        validationObject.status = HttpStatus.OK;
        validationObject.description = Description.REGISTER_DELETED;
        validationObject.additional = Description.REFERENCE_ID + id;

        return validationObject;
    }
    private ValidationObject registryCreate(Object objectCreate)
    {
        validationObject.status = HttpStatus.OK;
        validationObject.description = Description.REGISTER_CREATED;
        validationObject.additional = objectCreate;

        return validationObject;
    }
    private ValidationObject registryUpdate(Object object)
    {
        validationObject.status = HttpStatus.OK;
        validationObject.description = Description.REGISTER_UPDATED;
        validationObject.additional = object;

        return validationObject;
    }

    /**
     * Global validationObject for _ID
     * @param _id
     * @return
     */
    public Boolean idValidation(Object _id){

        return _id != null;
    }

    //responses montados
    public ResponseEntity NOT_CONTAINS_ID(){
        return new ResponseEntity<Object>(notContainsID(), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity CONTAINS_FIELDS_IMMUTABLE(String fields){
        return new ResponseEntity<Object>(containsFieldsImmutable(fields), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity NOT_CONTAINS_FIELDS(String fields){
        return new ResponseEntity<Object>(notContainsFields(fields), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity NOT_DATA_BASE(){
        return new ResponseEntity<Object>(notDataBase(), HttpStatus.INSUFFICIENT_STORAGE);
    }

    public ResponseEntity REGISTRY_EXISTED(String field){
        return new ResponseEntity<Object>(existing(field), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity ITEM_NOT_FOUND(Object object){
        return new ResponseEntity<Object>(notFound(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity THAT_OK(Object object){
        return new ResponseEntity<Object>(object, HttpStatus.OK);

    }

    public ResponseEntity REGISTRY_DELETED(Object id){
        return new ResponseEntity<Object>(registryDeleted(id.toString()), HttpStatus.OK);
    }

    public ResponseEntity REGISTRY_CREATE(Object objectCreate){
        return new ResponseEntity<Object>(registryCreate(objectCreate), HttpStatus.OK);
    }

    public ResponseEntity REGISTRY_UPDATE(Object objectUpdate){
        return new ResponseEntity<Object>(registryUpdate(objectUpdate), HttpStatus.OK);
    }


    public ResponseEntity REQUEST_NOT_AUTHORIZED(){
        return new ResponseEntity<Object>(notAutorized(), HttpStatus.UNAUTHORIZED);
    }
}
