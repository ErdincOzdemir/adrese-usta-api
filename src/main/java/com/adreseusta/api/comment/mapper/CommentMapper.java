package com.adreseusta.api.comment.mapper;

import com.adreseusta.api.comment.api.response.GetCommentResponse;
import com.adreseusta.api.comment.persistence.entity.Comment;
import com.adreseusta.api.comment.service.dto.CommentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    List<GetCommentResponse> toGetCommentResponseList(List<CommentDTO> commentDTOList);

    GetCommentResponse toGetCommentResponse(CommentDTO commentDTO);

    List<CommentDTO> toCommentDTOList(List<Comment> commentList);

    CommentDTO toCommentDTO(Comment comment);
}
