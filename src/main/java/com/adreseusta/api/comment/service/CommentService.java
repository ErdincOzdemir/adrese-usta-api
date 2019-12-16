package com.adreseusta.api.comment.service;

import com.adreseusta.api.comment.service.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getComments(Integer count);
}
