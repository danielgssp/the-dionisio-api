package com.thedionisio.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thedionisio.model.bss.CompanyBss;
import com.thedionisio.util.mongo.Mongo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 3/6/17.
 */
public class Event {


    @Override
    public String toString() {
        return "Event{" +
                "_id=" + _id +
                ", _idCompany=" + _idCompany +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateTimeRange=" + dateTimeRange +
                ", genres=" + genres +
                ", urlBanners=" + urlBanners +
                ", batches=" + batches +
                ", tickets=" + tickets +
                ", place=" + place +
                ", isActive=" + isActive +
                '}';
    }

    public Object _id;
    public String _idCompany;
    public String name;
    public String description;
    public DateTimeRange dateTimeRange;
    public List<String> genres;
    public List<String> urlBanners;
    public List<Batch> batches;
    @JsonIgnore()
    public List<Ticket> tickets;
    public Place place;
    public Boolean isActive;


    @JsonIgnore
    public Event treatCreate(){
        this.isActive = true;
        if(this.name != null ){this.name = this.name.toLowerCase();}
        if(this.description != null ){this.description = this.description.toLowerCase();}

        if(this.genres != null)
        {
            List<String> genresLowerCase = new ArrayList<>();
            this.genres.forEach(g->genresLowerCase.add(g.toLowerCase()));
            this.genres = genresLowerCase;
        }

        this._id = null;
        this.isActive = true;
        return this;
    }

    @JsonIgnore()
    public String isRequired(){
        return " < _idCompany, name, dateTimeRange >";
    }

    @JsonIgnore()
    public Boolean createValidation(){
        return  this.name!=null &&
                this.dateTimeRange!=null &&
                new CompanyBss().isActiveCompany(this._idCompany);
    }

    @JsonIgnore()
    public String attributeIdentifier(){return "email < ";}

    @JsonIgnore()
    public Event treatResponse()
    {
        this._id = Mongo.treatMongoId.toString(this._id);
        return this;
    }

}
