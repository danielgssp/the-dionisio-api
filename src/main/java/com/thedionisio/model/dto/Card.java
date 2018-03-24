package com.thedionisio.model.dto;

/**
 * Created by jonathan on 3/4/17.
 */
public class Card {


    @Override
    public String toString() {
        return "Card{" +
                "flag='" + flag + '\'' +
                ", validity='" + validity + '\'' +
                ", codSafe='" + codSafe + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", owner='" + owner + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }

    public String flag;
    public String validity;
    public String codSafe;
    public String cardNumber;
    public String owner;
    public Boolean isActive;



}
