package com.blogapp.blog.application.controller;

import com.blogapp.blog.application.dto.CommentDto;
import com.blogapp.blog.application.service.CommentService;
import exception.DeleteApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public record CommentController(CommentService commentService){

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable(value = "postId") Long postId)
    {
        CommentDto comment = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<DeleteApiResponse> deleteComment(@PathVariable(value = "commentId") Long commentId)
    {
       this.commentService.deleteComment(commentId);
       return new ResponseEntity<>(new DeleteApiResponse("comment has been deleted"),HttpStatus.OK);
    }
}
