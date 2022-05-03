package com.example.MeamaProject.files.boundary;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class MultipartFileAttachmentDTO {

    private List<@Valid FileAttachmentDTO> attachments = new ArrayList<>();

    public List<FileAttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<FileAttachmentDTO> attachments) {
        this.attachments = attachments;
    }

}
