package com.krunal.placeai.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.krunal.placeai.storage.FileStorageService;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(
            @RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("Controller Hit");
        System.out.println("File Name: " + file.getOriginalFilename());
        System.out.println("File Size: " + file.getSize());

        String fileName = fileStorageService.saveFile(file);

        return ResponseEntity.ok("Resume uploaded successfully: " + fileName);
    }
}