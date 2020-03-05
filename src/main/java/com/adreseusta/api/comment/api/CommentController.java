package com.adreseusta.api.comment.api;

import com.adreseusta.api.comment.api.response.GetCommentResponse;
import com.adreseusta.api.comment.mapper.CommentMapper;
import com.adreseusta.api.comment.service.CommentService;
import com.adreseusta.api.comment.service.dto.CommentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "comment controller")
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    private CommentService commentService;
    private CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @ApiOperation(value = "get comments")
    @GetMapping
    public ResponseEntity<List<GetCommentResponse>> getComments(@RequestParam Integer count) {
        List<CommentDTO> commentDTOList = commentService.getComments(count);
        return new ResponseEntity<>(commentMapper.toGetCommentResponseList(commentDTOList), HttpStatus.OK);
    }
}
