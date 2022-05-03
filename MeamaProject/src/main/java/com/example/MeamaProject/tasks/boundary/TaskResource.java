package com.example.MeamaProject.tasks.boundary;

import com.example.MeamaProject.files.boundary.MultipartFileAttachmentDTO;
import com.example.MeamaProject.tasks.entity.Task;
import com.example.MeamaProject.tasks.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

    private final TaskService service;

    public TaskResource(TaskService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('TASK_CREATE')")
    @PostMapping
    public ResponseEntity  createComplaint(@RequestPart @Valid TaskDTO task,
                                @Valid MultipartFileAttachmentDTO multipartFileAttachment,
                                HttpServletRequest request){
        Long taskId = service.createTask(task, multipartFileAttachment.getAttachments());
        try {
            return ResponseEntity.created(new URI(request.getRequestURL().append("/").append(taskId.toString()).toString())).build();
        } catch (URISyntaxException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('TASK_INSPECTION')")
    public Task getTask(@PathVariable("id") Long id){
        Task task = service.getTask(id);
        return task;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('TASK_DELETE')")
    public void deleteTask(@PathVariable("id") Long id){
        service.deleteTask(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('TASK_UPDATE')")
    public void updateTask(@PathVariable("id") Long id,
                           @RequestPart @Valid TaskDTO task,
                           @Valid MultipartFileAttachmentDTO multipartFileAttachment){
        service.update(id, task, multipartFileAttachment.getAttachments());
    }
}
