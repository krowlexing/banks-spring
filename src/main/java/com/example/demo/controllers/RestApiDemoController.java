package com.example.demo.controllers;

import com.example.demo.repositories.BankRepository;
import com.example.demo.parser.BankXmlParser;
import com.example.demo.data.BICDirectoryEntry;
import org.springframework.web.bind.annotation.*;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Optional;

@RestController
public class RestApiDemoController {
    private final BankRepository bankRepository;

    public RestApiDemoController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
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
        var handler = new BankXmlParser();
        handler.onEntryParsed = bankRepository::save;
        var file = new File("xml/2023.xml");
        if (file.exists()) {
            try {
                var parser = SAXParserFactory.newInstance().newSAXParser();
                parser.parse(file, handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("error");
        }
    }

    @GetMapping("/banks/{id}")
    Optional<BICDirectoryEntry> getCoffeeById(@PathVariable String id) {
        var instance = this.bankRepository.findById(Long.parseLong(id));

        return instance;
    }
}
