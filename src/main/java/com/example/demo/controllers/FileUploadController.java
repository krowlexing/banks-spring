package com.example.demo.controllers;

import com.example.demo.data.ED807;
import com.example.demo.services.StorageService;
import com.example.demo.services.ValidatorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@Log4j2
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
            log.info("multipart file upload - IO Exception - temporary store failed");
            e.printStackTrace();
        }

        storageService.store(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
}