package com.example.demo.services.implementations;

import com.example.demo.data.ED807;
import com.example.demo.services.ParsingService;
import com.example.demo.services.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FileSystemStorageService implements StorageService {

    private final ParsingService parsingService;
    private final Path rootLocation;

    public FileSystemStorageService(ParsingService parsingService) {
        this.parsingService = parsingService;

        var uploadDir = new File("upload");

        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            throw new RuntimeException("failed to prepare upload directory");
        }
        this.rootLocation = Paths.get("upload/");
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                log.error("ed807 upload: empty file");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                log.error("file parent: " + destinationFile.getParent() + "\n" +
                        "root: " + this.rootLocation.toAbsolutePath() + "\n" +
                        "cant store files outside cwd"
                );


            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<ED807> loadAll() {
        try {
            var paths = Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize)
                    .map(this.rootLocation::resolve);

            var ed807s = paths.map(path -> {
                var hashcode = path.hashCode();
                var ed807 =  processFile(path.toFile(), parsingService::parse);
                ed807.setId(Integer.valueOf(hashcode).longValue());
                return ed807;
            }).collect(Collectors.toList());

            log.info("ed807s request fulfilled");

            return ed807s;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("he");
        }

    }

    @Override
    public Optional<ED807> load(Long id) {
        return filebyid(id.intValue()).map(path -> processFile(path.toFile(), parsingService::parse));
    }


    static <T> T processFile(File file, Function<FileInputStream, T> consumer) {
        try (var stream = new FileInputStream(file)) {
            return consumer.apply(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    Optional<Path> filebyid(Integer id) {
        var files = hashes();
        var file = files.get(id);
        return Optional.of(file);
    }

    HashMap<Integer, Path> hashes() {
        try (var files = Files.list(Path.of("upload"))) {
            var list = files.toList();
            var map = new HashMap<Integer, Path>();
            for (var path : list) {
                map.put(path.hashCode(), path);
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Failed to load file");
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}