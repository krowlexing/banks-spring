package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ED807 {
    @Id
    Long id;

    int edNo;
    Date edDate;
    long author;
    @Nullable
    long receiver;
    String creationReason;
    Date creationDateTime;
    String infoTypeCode;
    Date businessDay;
    @Nullable
    int directoryVersion;

    @Singular
    @OneToMany(cascade = CascadeType.ALL)
    List<BICDirectoryEntry> entries;
}
