package com.example.demo.controllers;

import com.example.demo.data.ED807;
import com.example.demo.repositories.BankRepository;
import com.example.demo.parser.BankXmlParser;
import com.example.demo.data.BICDirectoryEntry;
import com.example.demo.repositories.Ed807Repository;
import com.example.demo.services.ParsingService;
import org.springframework.web.bind.annotation.*;

import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Optional;
import java.util.Timer;

import static java.time.temporal.ChronoUnit.MILLIS;

@RestController
@RequestMapping("/ed807/")
public class ED807Controller {
    private final Ed807Repository bankRepository;
    private final ParsingService parsingService;

    public ED807Controller(Ed807Repository bankRepository, ParsingService parsingService) {
        this.bankRepository = bankRepository;
        this.parsingService = parsingService;
    }

    @GetMapping("/")
    Iterable<ED807> getEntries() {
        return this.bankRepository.findAll();
    }

    @PostMapping("/{id}")
    void updateBank(@PathVariable String id, @RequestBody ED807 updatedEntry) {
        System.out.println("saving....");
        updatedEntry.setId(Long.parseLong(id));
        bankRepository.save(updatedEntry);
        System.out.println("saved");
    }

    @GetMapping("/{id}")
    Optional<ED807> getEntryById(@PathVariable String id) {
        var instance = this.bankRepository.findById(Long.parseLong(id));

        return instance;
    }
}
