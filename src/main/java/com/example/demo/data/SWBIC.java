package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

// swift bic
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class SWBIC {
    @Id
    @GeneratedValue
    Long id;

    @NonNull String swbic;
    @NonNull String defaultSWBIC;
}
