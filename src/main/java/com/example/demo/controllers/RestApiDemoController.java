package com.example.demo.controllers;

import com.example.demo.repositories.BankRepository;
import com.example.demo.parser.BankXmlParser;
import com.example.demo.data.BICDirectoryEntry;
import com.example.demo.services.ParsingService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class RestApiDemoController {
    private final BankRepository bankRepository;
    private final ParsingService parsingService;

    public RestApiDemoController(BankRepository bankRepository, ParsingService parsingService) {
        this.bankRepository = bankRepository;
        this.parsingService = parsingService;
    }



    @GetMapping("/banks")
    Iterable<BICDirectoryEntry> getCoffees() {
        return this.bankRepository.findAll();
    }

    @PostMapping("/banks/{bic}")
    void updateBank(@PathVariable String bic, @RequestBody BICDirectoryEntry updatedBank) {
        Optional<BICDirectoryEntry> maybeBank = bankRepository.findById(Long.parseLong(bic));
        if (maybeBank.isPresent()) {
            var bank = maybeBank.get();
            bank.setParticipantInfo(updatedBank.getParticipantInfo());
            bankRepository.save(bank);
        }
    }

    @GetMapping("/banks/{id}")
    Optional<BICDirectoryEntry> getCoffeeById(@PathVariable String id) {
        var instance = this.bankRepository.findById(Long.parseLong(id));

        return instance;
    }
}
