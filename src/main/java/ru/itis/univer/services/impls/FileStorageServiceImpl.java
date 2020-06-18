package ru.itis.univer.services.impls;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.univer.models.FileInfo;
import ru.itis.univer.repositories.FileInfoRepository;
import ru.itis.univer.services.FileStorageService;
import ru.itis.univer.utils.FileStorageUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final FileInfoRepository fileInfoRepository;
    private final FileStorageUtil fileStorageUtil;

    public FileStorageServiceImpl(FileInfoRepository fileInfoRepository,
                                  FileStorageUtil fileStorageUtil) {
        this.fileInfoRepository = fileInfoRepository;
        this.fileStorageUtil = fileStorageUtil;
    }

    @Override
    public String saveFile(MultipartFile file) {
        FileInfo fileInfo = fileStorageUtil.convertFromMultipart(file);
        fileInfoRepository.save(fileInfo);
        fileStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());
        return fileInfo.getStorageFileName();
    }

    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = fileInfoRepository.findOneByStorageFileName(fileName);
        response.setContentType(file.getType());
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new java.io.File(file.getUrl()));
            org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
