package com.example.demo.services;

import com.example.demo.data.ED807;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

// save files -> load ED807s
public interface StorageService {
    void store(MultipartFile file);

    Iterable<ED807> loadAll();

    Optional<ED807> load(Long id);

    void deleteAll();
}
