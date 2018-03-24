package com.thedionisio.model.dto;

import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jonathan on 5/21/17.
 */
public class EventFilterFind {
    public String name;
    public List<String> genres;
    public DateRange dateRange;
    public Location location;

    public Document getQueryDocument()
    {
        Document query = new Document();

        if (this.name!= null && !this.name.equals(""))
        {
            Document noun = new Document();
            noun.put("$regex", "/"+this.name+"/");
            query.put("name",this.name.toLowerCase());

        }

        if (this.dateRange!=null  && this.dateRange.start != null && this.dateRange.end != null)
        {

            Document start = new Document();
            Document end = new Document();

            if (this.dateRange.start.isEqual(this.dateRange.end))
            {
                start.put("$gt",this.dateRange.start.minusDays(1));
                end.put("$lt",this.dateRange.end.plusDays(1));
            }
            else
            {
                start.put("$gt",this.dateRange.start);
                end.put("$lt",this.dateRange.end);
            }

            query.put("dateTimeRange.start",start);
            query.put("dateTimeRange.end",end);


        }

        if (this.genres!= null && this.genres.size()>0)
        {

            Document genres = new Document();
            genres.append("$in", this.genres.stream().collect(Collectors.toList()));
            query.append("genres",genres);

        }

        return query;
    }

}
