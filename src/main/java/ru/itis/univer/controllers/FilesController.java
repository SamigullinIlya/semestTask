package ru.itis.univer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.univer.services.FileStorageService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class FilesController {

    private final FileStorageService service;

    public FilesController(FileStorageService service) {
        this.service = service;
    }

    @GetMapping("/storage")
    public String getStoragePage() {
        return "file_upload";
    }

    @PostMapping("/files")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String filePath = service.saveFile(file);
        return ResponseEntity
                .ok()
                .body(filePath);
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.writeFileToResponse(fileName, response);
    }

}
