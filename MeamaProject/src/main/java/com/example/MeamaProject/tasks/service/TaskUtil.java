package com.example.MeamaProject.tasks.service;

import com.example.MeamaProject.exception.BusinessException;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TaskUtil {

    public static void saveFile(MultipartFile file, Path attachmentPath, Path folder) throws IOException {
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }
        try {
            Files.copy(file.getInputStream(), attachmentPath);
        } catch (IOException e) {
            throw new BusinessException();
        }
    }

    public static void deleteDirectory(Path path) throws IOException {
        FileSystemUtils.deleteRecursively(path);
    }
}
