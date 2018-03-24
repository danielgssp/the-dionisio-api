package com.thedionisio.model.dto;

/**
 * Created by jonathan on 3/6/17.
 */
public class Batch {


    @Override
    public String toString() {
        return "Batch{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sector='" + sector + '\'' +
                ", dateTimeRange=" + dateTimeRange +
                ", limit=" + limit +
                ", sold=" + sold +
                ", price=" + price +
                ", openBar=" + openBar +
                ", isActive=" + isActive +
                '}';
    }

    public String name;
    public String description;
    public String sector;
    public DateTimeRange dateTimeRange;
    public Integer limit;
    public Integer sold;
    public Price price;
    public OpenBar openBar;
    public Boolean isActive;



}
