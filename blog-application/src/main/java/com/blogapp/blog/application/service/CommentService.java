package com.blogapp.blog.application.service;

import com.blogapp.blog.application.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto comment, Long postId);
    void deleteComment(Long CommentId);
}
