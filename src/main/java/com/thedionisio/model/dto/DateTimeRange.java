package com.thedionisio.model.dto;

import java.time.LocalDateTime;

/**
 * Created by jonathan on 3/6/17.
 */
public class DateTimeRange {


    @Override
    public String toString() {
        return "DateTimeRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public LocalDateTime start;
    public LocalDateTime end;
}
