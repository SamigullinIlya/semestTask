package ru.itis.univer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.univer.models.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findOneByStorageFileName(String storageFileName);
}

