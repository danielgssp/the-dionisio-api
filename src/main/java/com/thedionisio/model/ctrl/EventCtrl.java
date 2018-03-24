package com.thedionisio.model.ctrl;

import com.thedionisio.dao.EventRepository;
import com.thedionisio.model.bss.EventBss;
import com.thedionisio.model.dto.*;
import com.thedionisio.util.verification.Validation;
import org.bson.Document;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 3/11/17.
 */
public class EventCtrl {

    private EventBss eventBss = new EventBss();
    private EventRepository eventRepository = new EventRepository();
    private final String collection  ="event";

    public Object create(Event event){

        try
        {
            if(event.createValidation())
            {
                if (eventBss.existingValidation(collection, event))
                {
                    ResponseEntity responseEntity = eventRepository.create(collection, event.treatCreate());
                    try
                    {
                        if (Boolean.parseBoolean(responseEntity.getBody().toString()))
                        {
                           return this.find();
                        }
                    }
                    catch (Exception e)
                    {
                        return responseEntity;
                    }

                }
                return Validation.resquest.REGISTRY_EXISTED(event.attributeIdentifier() + event.name);
            }
            return Validation.resquest.NOT_CONTAINS_FIELDS(event.isRequired());
        }
        catch (Exception e)
        {
            return Validation.resquest.NOT_DATA_BASE();
        }

    }

    public Object find(){

        Object objectFind  = eventRepository.find(collection,new Event(), new Document(), new Document(),0);
        try
        {
            List<Event> events = (List<Event>) objectFind;
            return eventBss.treatResponse(events);
        }
        catch (Exception e)
        {
            return objectFind;
        }
    }

    public Object findBy(EventFilterFind eventFilterFind){
        Object objectFind  = eventRepository.find(collection,new Event(), eventFilterFind.getQueryDocument(), new Document(),0);
        try
        {
            List<Event> events = (List<Event>) objectFind;
            return eventBss.treatResponse(events);
        }
        catch (Exception e)
        {
            return objectFind;
        }
    }

    public Object findByCompany(String id){
        return eventRepository.findByCompany(id);
    }

    public Object findOne(Object id){
        Object objectFind  = eventRepository.findOne(collection,id,new Event());

        try
        {
            List<Event> events = (List<Event>) objectFind;
            return eventBss.treatResponse(events);
        }
        catch (Exception e)
        {
            return objectFind;
        }


    }

    public Object update(Event event){

        try
        {
            if(Validation.resquest.idValidation(event._id))
            {
                Object objectResponse = eventRepository.updateBlock(collection, event._id, event);
                try
                {
                    if((Boolean) objectResponse)
                    {
                        return Validation.resquest.REGISTRY_UPDATE(event._id);
                    }
                }catch (Exception e)
                {
                    return objectResponse;
                }

            }
            return Validation.resquest.NOT_CONTAINS_ID();
        }
        catch (Exception e)
        {
            return Validation.resquest.NOT_DATA_BASE();
        }
    }

    public Object removeOne(Event event){
        //eventRepository.removeOne(event._id,collection);
        event.isActive =false;
        Object response = eventRepository.update(event,event._id,collection);
        try
        {
            List<Event> eventoRemoved = (List<Event>) response;
            if (eventoRemoved.size()>0)
            return Validation.resquest.REGISTRY_DELETED(event._id);
        }
        catch (Exception e)
        {

        }
        return response;
    }

}


