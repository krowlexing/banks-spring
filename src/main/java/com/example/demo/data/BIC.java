package com.example.demo.data;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.security.InvalidParameterException;

/**
 * 9-digit code Банковский идентификационный код
 */

public class BIC {
    @Getter
    final long value;
    BIC(String string) {
        this.value = Long.parseLong(string);
    }

    public static BIC from(String string) {
        if (string == null) return null;

        if (string.length() == 9 && string.matches("^\\d+$")) {
            return new BIC(string);
        } else {
            throw new InvalidParameterException("bic must be 9 digits long");
        }
    }


}
