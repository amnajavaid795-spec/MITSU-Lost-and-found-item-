package com.mitsu.mitsu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Only image files are allowed"));
            }

            Path dir = Paths.get(uploadDir);
            Files.createDirectories(dir);

            String ext = "";
            String original = file.getOriginalFilename();
            if (original != null && original.contains(".")) {
                ext = original.substring(original.lastIndexOf("."));
            }

            String filename = UUID.randomUUID() + ext;
            Path dest = dir.resolve(filename);
            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

            String url = "/uploads/" + filename;
            return ResponseEntity.ok(Map.of("url", url));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Upload failed: " + e.getMessage()));
        }
    }
}
