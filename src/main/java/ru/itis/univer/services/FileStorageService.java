package ru.itis.univer.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileStorageService {
    String saveFile(MultipartFile file);

    void writeFileToResponse(String fileName, HttpServletResponse response);
}
