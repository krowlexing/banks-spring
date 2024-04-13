package com.example.demo.data;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class AccountRestriction {
    @Id
    @GeneratedValue
    long id;

    @NonNull String accountRestriction;
    @NonNull Date accountRestrictionDate;
    @Nullable
    long successorBIC;

    public AccountRestriction(String accountRestriction, Date accountRestrictionDate, @Nullable BIC successorBIC) {
        setSuccessorBIC(successorBIC);
        setAccountRestriction(accountRestriction);
        setAccountRestrictionDate(accountRestrictionDate);
    }

    public void setSuccessorBIC(BIC successorBIC) {
        if (successorBIC != null)
            this.successorBIC = successorBIC.getValue();
    }
}
