package com.thedionisio.model.dto;

/**
 * Created by jonathan on 3/6/17.
 */
public class Location {
    @Override
    public String toString() {
        return "Location{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    public String latitude;
    public String longitude;
}
