package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Date;

@RequiredArgsConstructor
@Entity
@NoArgsConstructor

public class Restriction {
    @Id @GeneratedValue
    Long id;
    @NonNull String rstr;
    @NonNull Date rstrDate;
}
