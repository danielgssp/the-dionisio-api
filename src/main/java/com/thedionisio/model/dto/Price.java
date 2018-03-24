package com.thedionisio.model.dto;

/**
 * Created by jonathan on 3/7/17.
 */
public class Price {


    @Override
    public String toString() {
        return "Price{" +
                "man=" + man +
                ", other=" + other +
                ", woman=" + woman +
                '}';
    }

    public Double man;
    public Double other;
    public Double woman;
}
