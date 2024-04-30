package com.example.demo.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import com.example.demo.data.ED807;
import com.example.demo.services.StorageService;
import com.example.demo.services.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("files")
public class FileUploadController {

    private final StorageService storageService;
    private final ValidatorService validatorService;

    @Autowired
    public FileUploadController(StorageService storageService, ValidatorService validatorService) {
        this.storageService = storageService;
        this.validatorService = validatorService;
    }

    @GetMapping("/all")
    public Iterable<ED807> handleAllEntries() {
        return storageService.loadAll();
    }

    @GetMapping("/error")
    String handleError() {
        return "something happened";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try (var inputStream = file.getInputStream()){
            validatorService.validate(inputStream);
        } catch (IOException e) {
            System.out.println("multipart file upload - IO Exception - temporary store failed");
            e.printStackTrace();
        }

        storageService.store(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
}