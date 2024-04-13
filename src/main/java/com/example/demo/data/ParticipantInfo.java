package com.example.demo.data;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder(builderClassName = "Bulldozer")
@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantInfo {
    @Id
    @GeneratedValue
    long id;

    String nameP;
    @Nullable
    String englName;
    @Nullable
    String regN;
    @Nullable
    String cntrCd;
    String rgn;
    @Nullable
    String ind;
    String tnp;
    @Nullable
    String nnp;
    @Nullable
    String adr;
    @Nullable
    long prntBIC;
    Date dateIn;
    @Nullable
    Date dateOut;
    String ptType;
    String svrvcs;
    String xchType;
    long uid;
    @Nullable
    String participantStatus;
    @Singular @OneToMany(cascade = CascadeType.ALL)
    List<Restriction> restrictions;

    public static class Bulldozer {
        public Bulldozer uid(UID uid) {
            this.uid = uid.value;
            return this;
        }

        public Bulldozer prntBIC(BIC bic) {
            if (bic != null)
                this.prntBIC = bic.getValue();
            return this;
        }
    }
}
