package com.example.demo.controllers;

import com.example.demo.data.ED807;
import com.example.demo.repositories.Ed807Repository;
import com.example.demo.services.ParsingService;
import com.example.demo.services.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ed807/")
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
        System.out.println("saving....");
        updatedEntry.setId(Long.parseLong(id));
        bankRepository.save(updatedEntry);
        System.out.println("saved");
    }

    @GetMapping("/{id}")
    Optional<ED807> getEntryById(@PathVariable String id) {
        var longId = Long.parseLong(id);
        System.out.println("Get /ed807/" + longId);
        var instance = this.storageService.load(longId);

        if (instance.isEmpty()) {
            System.out.println("none found");
        }

        return instance;
    }
}
