package com.example.MeamaProject.tasks.service;

import com.example.MeamaProject.files.service.FileAttachmentService;
import com.example.MeamaProject.files.boundary.FileAttachmentDTO;
import com.example.MeamaProject.tasks.boundary.TaskDTO;
import com.example.MeamaProject.tasks.entity.Task;
import com.example.MeamaProject.tasks.repository.TaskRepository;
import com.example.MeamaProject.users.entity.User;
import com.example.MeamaProject.users.repository.UserRepository;
import com.example.MeamaProject.exception.BusinessException;
import com.example.MeamaProject.exception.SecurityViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TaskService {

    @Value("${app.mediaDir}")
    String mediaDir;

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final FileAttachmentService fileAttachmentService;

    public TaskService(UserRepository userRepository, TaskRepository taskRepository, FileAttachmentService fileAttachmentService) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.fileAttachmentService = fileAttachmentService;
    }


    public Long createTask(TaskDTO task, List<FileAttachmentDTO> attachments) {
        Task newTask = new Task();
        newTask.setName(task.getName());
        newTask.setLongDescription(task.getLongDescription());
        newTask.setShortDescription(task.getShortDescription());

        String username = task.getUsername();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            newTask.setUser(user);
        }
        Long taskId = taskRepository.saveAndFlush(newTask).getId();
        saveFiles(attachments, taskId);
        return taskId;
    }

    public Task getTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new SecurityViolationException();
        }
        return taskOptional.get();
    }

    public void deleteTask(Long id) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(id);
            if (taskOptional.isEmpty()) {
                throw new SecurityViolationException();
            }
            Task task = taskOptional.get();
            taskRepository.delete(task);
            taskRepository.flush();
            TaskUtil.deleteDirectory(Path.of(mediaDir + File.separator + "Files"
                    + File.separator + id));
        } catch (PersistenceException | IOException ex) {
            throw new BusinessException("cant_delete");
        }
    }

    public void update(Long id, TaskDTO taskDTO, List<FileAttachmentDTO> attachments) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(id);
            if (taskOptional.isEmpty()) {
                throw new SecurityViolationException();
            }
            Task task = taskOptional.get();
            task.setName(taskDTO.getName());
            task.setShortDescription(taskDTO.getShortDescription());
            task.setLongDescription(taskDTO.getLongDescription());
            String username = taskDTO.getUsername();
            User user = userRepository.findByUsername(username);
            if (user != null) {
                task.setUser(user);
            }
            Long taskId = taskRepository.saveAndFlush(task).getId();
            saveFiles(attachments, taskId);
        } catch (SecurityViolationException e) {
            throw new SecurityViolationException();
        }

    }


    private void saveFiles(List<FileAttachmentDTO> attachments, Long id){
        if (attachments != null && !attachments.isEmpty()) {
            String folderPath = mediaDir + File.separator + "Files"
                    + File.separator + id;
            try {
                fileAttachmentService.saveAttachments(attachments, folderPath, id);
            } catch (Exception ex) {
                throw new BusinessException("cant_create");
            }
        }
    }
}
