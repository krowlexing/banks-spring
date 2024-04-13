package com.example.demo.data;

/**
 * Если InfoTypeCode = "SIRR", иначе null
 */
public enum ChangeType {
    ADDD("ADDD"),
    CHGD("CHGD"),
    DLTD("DLTD");

    private final String type;

    ChangeType(final String text) {
        this.type = text;
    }

    public static ChangeType from(final String text) {
        // TODO: map string to ChangeType;
        return ChangeType.ADDD;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
