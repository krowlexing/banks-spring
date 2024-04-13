package com.example.demo.data;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder(builderClassName = "Builder")
@ToString
public class BICDirectoryEntry {
    BIC bic;
    @Nullable
    ChangeType changeType;
    ParticipantInfo participantInfo;
    @Singular
    List<SWBIC> swbics;
    @Singular
    List<Account> accounts;

    public BICDirectoryEntry(BIC bic, ChangeType changeType, ParticipantInfo participantInfo, List<SWBIC> swbics, List<Account> accounts) {
        this.bic = bic;
        this.changeType = changeType;
        this.participantInfo = participantInfo;
        this.swbics = swbics;
        this.accounts = accounts;
    }
}
