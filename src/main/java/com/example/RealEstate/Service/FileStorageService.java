package com.example.RealEstate.Service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Getter
public class FileStorageService {

    private final Path uploadRoot;

    // ✅ Constructor (create upload folder)
    public FileStorageService(@Value("${app.file-upload-dir:uploads}") String uploadDir) throws IOException {
        this.uploadRoot = Path.of(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.uploadRoot);

        System.out.println("✅ Upload root path: " + this.uploadRoot.toAbsolutePath());

        if (Files.isWritable(this.uploadRoot)) {
            System.out.println("✅ Upload folder is writable");
        } else {
            System.out.println("❌ Upload folder is NOT writable");
        }
    }

    // ✅ STORE SINGLE IMAGE (FINAL FIXED VERSION)
    public String storePropertyImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Image file is required");
        }

        try {
            // 🔥 SAFE filename handling
            String originalFilename = file.getOriginalFilename();

            if (originalFilename == null || originalFilename.isBlank()) {
                originalFilename = "image";
            }

            // 🔥 Extract extension safely
            String extension = "";
            int dotIndex = originalFilename.lastIndexOf(".");
            if (dotIndex >= 0) {
                extension = originalFilename.substring(dotIndex);
            }

            // 🔥 Unique filename
            String storedFilename = UUID.randomUUID() + extension;

            Path destination = uploadRoot.resolve(storedFilename);

            // 🔥 Save file
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("✅ Stored file: " + destination.toAbsolutePath());

            // 🔥 Return URL path
            return "/uploads/" + storedFilename;

        } catch (Exception ex) {
            ex.printStackTrace(); // 🔥 VERY IMPORTANT DEBUG
            throw new RuntimeException("Failed to store image file");
        }
    }

    // ✅ STORE MULTIPLE FILES
    public List<String> storeMultipleFiles(MultipartFile[] files) {

        List<String> urls = new ArrayList<>();

        if (files == null || files.length == 0) {
            return urls;
        }

        for (MultipartFile file : files) {
            urls.add(storePropertyImage(file));
        }

        return urls;
    }

    // ✅ DELETE FILE
    public void deleteFile(String fileUrl) {

        if (fileUrl == null || fileUrl.isBlank() || !fileUrl.startsWith("/uploads/")) {
            return;
        }

        try {
            String filename = fileUrl.substring("/uploads/".length());
            Path filePath = uploadRoot.resolve(filename).normalize();

            Files.deleteIfExists(filePath);

            System.out.println("✅ Deleted file: " + filePath.toAbsolutePath());

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to delete file");
        }
    }
}