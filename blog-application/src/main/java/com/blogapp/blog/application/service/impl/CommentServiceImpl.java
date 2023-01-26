package com.blogapp.blog.application.service.impl;

import com.blogapp.blog.application.dto.CommentDto;
import com.blogapp.blog.application.entity.Comment;
import com.blogapp.blog.application.entity.Post;
import com.blogapp.blog.application.repo.CommentRepo;
import com.blogapp.blog.application.repo.PostRepo;
import com.blogapp.blog.application.service.CommentService;
import exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public record CommentServiceImpl(PostRepo postRepo, CommentRepo commentRepo,ModelMapper mapper) implements CommentService{
    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post","postId",postId));
        Comment comment = mapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment save = commentRepo.save(comment);
        return mapper.map(save,CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {
      Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFound("comment","commentId",commentId));
      this.commentRepo.delete(comment);
    }
}
