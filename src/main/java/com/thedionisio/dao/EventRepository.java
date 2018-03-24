package com.thedionisio.dao;

import com.thedionisio.model.bss.EventBss;
import com.thedionisio.model.dto.Event;
import org.bson.Document;

import java.util.List;

/**
 * Created by jonathan on 3/11/17.
 */
public class EventRepository extends SimpleCrudRepository {

    private final String collection  ="event";
    private EventBss eventBss = new EventBss();

    public Object findByCompany(String id){

        Document query = new Document();
        query.append("_idCompany",id);
        query.append("isActive",true);
        Document sort = new Document();
        sort.append("dateTimeRange.start",-1);
        Object objectFind  = super.find(collection,new Event(), query, sort,0);
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

    public Boolean isActive(Object id)
    {
        try
        {
            List<Event> events = (List<Event>) super.findOne(collection,id,new Event());

            return events.get(0).isActive;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
