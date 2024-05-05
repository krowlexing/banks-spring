package com.example.demo.controllers;

import com.example.demo.data.ED807;
import com.example.demo.repositories.Ed807Repository;
import com.example.demo.services.ParsingService;
import com.example.demo.services.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ed807/")
@Log4j2
public class ED807Controller {
    private final Ed807Repository bankRepository;
    private final ParsingService parsingService;
    private final StorageService storageService;

    public ED807Controller(Ed807Repository bankRepository, ParsingService parsingService, StorageService storageService) {
        this.bankRepository = bankRepository;
        this.parsingService = parsingService;
        this.storageService = storageService;
    }

    @GetMapping("/")
    Iterable<ED807> getEntries() {
        return this.storageService.loadAll();
    }

    @PostMapping("/{id}")
    void updateBank(@PathVariable String id, @RequestBody ED807 updatedEntry) {
        log.info("saving....");
        updatedEntry.setId(Long.parseLong(id));
        bankRepository.save(updatedEntry);
        log.info("saved");
    }

    @GetMapping("/{id}")
    Optional<ED807> getEntryById(@PathVariable String id) {
        var longId = Long.parseLong(id);
        log.info("Get /ed807/" + longId);
        var instance = this.storageService.load(longId);

        if (instance.isEmpty()) {
            log.info("ed807 entry with id " + longId + " is not found");
        }

        return instance;
    }
}
