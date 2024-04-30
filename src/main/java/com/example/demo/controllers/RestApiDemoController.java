package com.example.demo.controllers;

import com.example.demo.repositories.BankRepository;
import com.example.demo.parser.BankXmlParser;
import com.example.demo.data.BICDirectoryEntry;
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

    @GetMapping("/banks/init/init")
    void initBanks() {
        var file = new File("xml/2023.xml");
        try (var stream = new FileInputStream(file)){
            var ed807 = parsingService.parse(stream);

            if (ed807 != null) {
                System.out.println("saving ed807 entries");
                var before = Instant.now();
                bankRepository.saveAll(ed807.getEntries());
                var after = Instant.now();

                var elapsed = before.until(after, MILLIS);
                System.out.println("saving complete. Elapsed " + elapsed + " ms");
            }
        } catch (FileNotFoundException e){
            System.out.println("file not found");
            System.out.println("error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/banks/{id}")
    Optional<BICDirectoryEntry> getCoffeeById(@PathVariable String id) {
        var instance = this.bankRepository.findById(Long.parseLong(id));

        return instance;
    }
}
