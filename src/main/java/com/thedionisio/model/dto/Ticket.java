package com.thedionisio.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thedionisio.dao.CompanyRepository;
import com.thedionisio.dao.EventRepository;
import com.thedionisio.dao.PersonRepository;
import com.thedionisio.dao.TicketRepository;
import com.thedionisio.util.mongo.Mongo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jonathan on 3/7/17.
 */
public class Ticket {

    private static PersonRepository personRepository = new PersonRepository();
    private static CompanyRepository companyRepository = new CompanyRepository();
    private static EventRepository eventRepository = new EventRepository();
    private static TicketRepository ticketRepository = new TicketRepository();

    @Override
    public String toString() {
        return "Ticket{" +
                "_id=" + _id +
                ", _idPerson=" + _idPerson +
                ", _idCompany=" + _idCompany +
                ", _idEvent=" + _idEvent +
                ", batch=" + batch +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                ", openBar=" + openBar +
                ", isActive=" + isActive +
                '}';
    }

    public Object _id;
    public Object _idPerson;
    public Object _idCompany;
    public Object _idEvent;
    public Batch batch;
    public LocalDateTime purchaseDate;
    public Price price;
    public OpenBar openBar;
    @JsonIgnore
    public Person person;
    public Boolean isActive;

    public boolean createValidation() {
        return validationForNullFields() &&
               validationForIsActiveEntity() &&
               !isExist();

    }

    private boolean validationForIsActiveEntity() {

        if (personRepository.isActive(this._idPerson))
        {
            List<Person> people = (List<Person>) personRepository.findOne("person",this._idPerson,new Person());
            this.person = people.get(0);
        }

        return personRepository.isActive(this._idPerson) &&
               companyRepository.isActive(this._idCompany) &&
               eventRepository.isActive(this._idEvent);
    }

    private boolean validationForNullFields() {
        return this._idCompany != null &&
               this._idPerson != null &&
               this._idEvent != null &&
               this.batch != null &&
               !this._idCompany.equals("")&&
               !this._idPerson.equals("")&&
               !this._idEvent.equals("");
    }

    private boolean isExist(){
        return ticketRepository.isExist(this);
    }

    @JsonIgnore
    public Ticket treatCreate() {
        this.isActive = true;
        this._id = null;
        this.purchaseDate = LocalDateTime.now();
        return this;
    }

    @JsonIgnore
    public String isRequiredForCreate() {
        return " < _idCompany, _idPerson, batch >";
    }

    @JsonIgnore
    public String attributeIdentifier(){return " _id < ";}

    @JsonIgnore
    public boolean updateValidation() {
        //valida update
        return true;
    }

    @JsonIgnore
    public Boolean treatUpdate() {
        //trata updade
        return true;
    }

    @JsonIgnore
    public Ticket treatResponse() {
        this._id = Mongo.treatMongoId.toString(this._id);
        return this;
    }

    @JsonIgnore
    public String isImmutable() {
        return " < _idCompany, _idPerson >";
    }
}




