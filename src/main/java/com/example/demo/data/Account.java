package com.example.demo.data;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder(builderClassName = "Builder")
public class Account {
    String account;
    String regulationAccountType;
    @Nullable
    String ck;
    BIC accountCBRBIC;
    DateString dateIn;
    @Nullable
    DateString dateOut;
    @Nullable
    String accountStatus;
    @Singular
    List<AccountRestriction> accountRestrictions;
}

