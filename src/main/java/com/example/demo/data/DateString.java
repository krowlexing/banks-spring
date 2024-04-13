package com.example.demo.data;

/**
 * DateString = "YYYY-MM-DD";
 */

public class DateString {
    final String value;

    DateString(final String value) {
        this.value = value;
    }

    public static DateString from(final String value) {
        if (value == null) {
            return null;
        }
        return new DateString(value);
    }
}
