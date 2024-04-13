package com.example.demo.data;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.Date;
import java.util.List;

@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    Long id;
    String account;
    String regulationAccountType;
    @Nullable
    String ck;
    long accountCBRBIC;
    Date dateIn;
    @Nullable
    Date dateOut;
    @Nullable
    String accountStatus;
    @Singular
    @OneToMany(cascade = CascadeType.ALL)
    List<AccountRestriction> accountRestrictions;

    public static class Builder {
        public Builder accountCBRBIC(BIC bic) {
            this.accountCBRBIC = bic.getValue();
            return this;
        }
    }

}

