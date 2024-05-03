package com.example.demo.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * DateString = "YYYY-MM-DD";
 */

public class DateString {
    final String value;

    DateString(final String value) {
        this.value = value;
    }

    public static Date from(final String value) {
        if (value == null) {
            return null;
        }
        var year = Integer.parseInt(value.substring(0, 4));
        var month = Integer.parseInt(value.substring(5, 7));
        var day = Integer.parseInt(value.substring(8, 10));
        var unixTime= LocalDate.of(year, month, day).toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.ofHours(0));
        return new Date(unixTime * 1000);
    }
}
