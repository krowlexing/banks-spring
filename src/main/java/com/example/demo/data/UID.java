package com.example.demo.data;

// 10 digit code
public class UID {
    final String value;

    UID(final String value) {
        this.value = value;
    }

    public static UID from(final String value) {
        return new UID(value);
    }
}
