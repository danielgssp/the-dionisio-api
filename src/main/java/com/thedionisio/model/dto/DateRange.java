package com.thedionisio.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by jonathan on 3/6/17.
 */
public class DateRange {


    @Override
    public String toString() {
        return "DateTimeRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public LocalDate start;
    public LocalDate end;
}
