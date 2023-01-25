package com.blogapp.blog.application.service;

import com.blogapp.blog.application.dto.PageDto;
import com.blogapp.blog.application.dto.PostDto;

import java.util.List;

public interface PostService{
    PostDto createPost(PostDto postDto, Long user_id, Long categoryId);

    PostDto updatePost(PostDto postDto, Long postId);

    PostDto getPostById(Long postId);

    void deletePost(Long postId);

    PageDto getAllPost(Integer pageNumber, Integer pageSize,String sortBy);

    List<PostDto> getAllPostByUser(Long user_id);

    List<PostDto> getAllPostByCategory(Long categoryId);

    List<PostDto> searchByKeyWord(String keyword);
}
