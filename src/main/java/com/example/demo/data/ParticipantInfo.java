package com.example.demo.data;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder(builderClassName = "Builder")
@Data
@ToString
public class ParticipantInfo {
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
    BIC prntBIC;
    DateString dateIn;
    @Nullable
    DateString dateOut;
    String ptType;
    String svrvcs;
    String xchType;
    UID uid;
    @Nullable
    String participantStatus;
    @Singular
    List<Restriction> restrictions;
}
