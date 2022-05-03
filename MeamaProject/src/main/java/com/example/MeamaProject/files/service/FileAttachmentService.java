package com.example.MeamaProject.files.service;

import com.example.MeamaProject.tasks.service.TaskUtil;
import com.example.MeamaProject.files.boundary.FileAttachmentDTO;
import com.example.MeamaProject.files.entity.FileAttachment;
import com.example.MeamaProject.files.repository.FileAttachmentRepository;
import com.example.MeamaProject.tasks.entity.Task;
import com.example.MeamaProject.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FileAttachmentService {

    private final TaskRepository taskRepository;
    private final FileAttachmentRepository fileAttachmentRepository;

    public FileAttachmentService(TaskRepository taskRepository, FileAttachmentRepository fileAttachmentRepository) {
        this.taskRepository = taskRepository;
        this.fileAttachmentRepository = fileAttachmentRepository;
    }

    public List<FileAttachment> saveAttachments(List<FileAttachmentDTO> attachmentDTOS, String folderPath, Long taskId) throws IOException {
        List<FileAttachment> result = new ArrayList<>();
        for (FileAttachmentDTO attachmentDTO : attachmentDTOS) {
            result.add(saveAttachment(attachmentDTO, folderPath, taskId));
        }
        return result;
    }

    public FileAttachment saveAttachment(FileAttachmentDTO attachmentDTO, String folderPath, Long taskId) throws IOException {
        MultipartFile file = attachmentDTO.getFile();
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setName(file.getOriginalFilename());
        fileAttachment.setFolderPath(folderPath);
        Task task = taskRepository.findById(taskId).get();
        if(task != null){
            fileAttachment.setTask(task);
        }
        fileAttachmentRepository.saveAndFlush(fileAttachment);

        Path taskFolder = Paths.get(folderPath);
        Path attachmentPath = taskFolder.resolve(fileAttachment.getId().toString());
        TaskUtil.saveFile(file, attachmentPath, taskFolder);
        return fileAttachment;
    }
}
