package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder(builderClassName = "Bulldozer")
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(Include.NON_NULL)
public class BICDirectoryEntry {
    @Id
    long bic;
    @Nullable
    ChangeType changeType;
    @OneToOne(cascade = CascadeType.ALL)
    @Setter
    ParticipantInfo participantInfo;
    @Singular
    @OneToMany(cascade = CascadeType.ALL)
    List<SWBIC> swbics;
    @Singular
    @OneToMany(cascade = CascadeType.ALL)
    List<Account> accounts;

    public BICDirectoryEntry(BIC bic, ChangeType changeType, ParticipantInfo participantInfo, List<SWBIC> swbics, List<Account> accounts) {
        setBic(bic);
        this.changeType = changeType;
        this.participantInfo = participantInfo;
        this.swbics = swbics;
        this.accounts = accounts;
    }

    public void setBic(BIC bic) {
        this.bic = bic.getValue();
    }
    public void setBic(long bic) {
        this.bic = bic;
    }

    public static class Bulldozer {
        public Bulldozer bic(BIC bic) {
            this.bic = bic.getValue();
            return this;
        }
    }
}
