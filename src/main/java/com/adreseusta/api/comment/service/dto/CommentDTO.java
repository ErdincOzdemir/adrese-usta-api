package com.adreseusta.api.comment.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private String fullName;
    private String title;
    private String comment;
    private byte[] image;
}
