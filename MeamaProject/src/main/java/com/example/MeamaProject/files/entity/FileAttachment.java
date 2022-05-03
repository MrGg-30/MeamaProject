package com.example.MeamaProject.files.entity;

import com.example.MeamaProject.tasks.entity.Task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "file_attachment")
public class FileAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file")
    @SequenceGenerator(name = "file", sequenceName = "seq_file_attachment", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 250)
    @Column(name = "folder_path", nullable = false)
    private String folderPath;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public FileAttachment(Long id, String name, String folderPath, Task task) {
        this.id = id;
        this.name = name;
        this.folderPath = folderPath;
        this.task = task;
    }

    public FileAttachment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
