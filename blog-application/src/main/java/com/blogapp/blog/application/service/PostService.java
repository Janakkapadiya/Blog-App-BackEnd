package com.blogapp.blog.application.service;

import com.blogapp.blog.application.dto.PostDto;

import java.util.List;

public interface PostService{
    PostDto createPost(PostDto postDto, Long user_id, Long categoryId);

    PostDto updatePost(PostDto postDto, Long postId);

    PostDto getPostById(Long postId);

    void deletePost(Long postId);

    List<PostDto> getAllPost();

    List<PostDto> getAllPostByUser(Long user_id);

    List<PostDto> getAllPostByCategory(Long categoryId);
}
