package com.thedionisio.model.dto;

/**
 * Created by jonathan on 3/6/17.
 */
public class OpenBar {
    @Override
    public String toString() {
        return "OpenBar{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public String name;
    public String description;
    public Boolean isActive;
}
