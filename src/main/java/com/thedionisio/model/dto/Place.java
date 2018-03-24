package com.thedionisio.model.dto;

/**
 * Created by jonathan on 3/6/17.
 */
public class Place {
    @Override
    public String toString() {
        return "Place{" +
                "description='" + description + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", answerable='" + answerable + '\'' +
                ", location=" + location +
                ", isActive=" + isActive +
                '}';
    }

    public String description;
    public String zipCode;
    public String city;
    public String street;
    public String number;
    public String phone;
    public String answerable;
    public Location location;
    public Boolean isActive;

}
