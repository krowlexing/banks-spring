package com.example.demo.data;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountRestriction {
    String accountRestriction;
    DateString accountRestrictionDate;
    @Nullable
    BIC successorBIC;
}
