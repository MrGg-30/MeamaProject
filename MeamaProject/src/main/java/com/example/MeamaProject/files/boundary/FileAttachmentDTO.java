package com.example.MeamaProject.files.boundary;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class FileAttachmentDTO {

    @NotNull
    private MultipartFile file;

    private Long id;

    private String author;

    public FileAttachmentDTO(MultipartFile file, Long id, String author) {
        this.file = file;
        this.id = id;
        this.author = author;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public FileAttachmentDTO() {
    }
}
