package com.example.MeamaProject.files.repository;

import com.example.MeamaProject.files.entity.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {
}

