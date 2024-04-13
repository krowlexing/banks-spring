package com.example.demo.data;

// 10 digit code
public class UID {
    final long value;

    UID(final String value) {
        this.value = Long.parseLong(value);
    }

    public static UID from(final String value) {
        return new UID(value);
    }
}
