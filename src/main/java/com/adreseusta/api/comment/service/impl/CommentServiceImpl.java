package com.adreseusta.api.comment.service.impl;

import com.adreseusta.api.comment.mapper.CommentMapper;
import com.adreseusta.api.comment.persistence.entity.Comment;
import com.adreseusta.api.comment.persistence.repository.CommentRepository;
import com.adreseusta.api.comment.service.CommentService;
import com.adreseusta.api.comment.service.dto.CommentDTO;
import com.adreseusta.api.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl extends BaseServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> getComments(Integer count) {
        List<Comment> commentList = commentRepository.getRandomComment(count);
        return commentMapper.toCommentDTOList(commentList);
    }
}
