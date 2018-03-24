package com.thedionisio.model.bss;


import com.thedionisio.model.dto.Event;
import com.thedionisio.util.mongo.Mongo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 3/6/17.
 */
public class EventBss {
    public Boolean existingValidation(String collection, Event event) {
        try
        {
            List list = new ArrayList();

            return list.size() <= 0;
        }
        catch (Exception e) {
            return null;
        }

    }


    public Object treatResponse(List<Event> events) {
        events.forEach(e -> {
             e.treatResponse();
        });

        Document response = new Document();
        response.put("events", events);
        return events;
    }

}
