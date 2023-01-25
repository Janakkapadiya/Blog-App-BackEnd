package com.blogapp.blog.application.service.impl;

import com.blogapp.blog.application.dto.PostDto;
import com.blogapp.blog.application.entity.Category;
import com.blogapp.blog.application.entity.Post;
import com.blogapp.blog.application.entity.User;
import com.blogapp.blog.application.repo.CategoryRepo;
import com.blogapp.blog.application.repo.PostRepo;
import com.blogapp.blog.application.repo.UserRepo;
import com.blogapp.blog.application.service.PostService;
import exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public record PostServiceImpl(ModelMapper modelMapper, PostRepo postRepo, UserRepo userRepo,
                              CategoryRepo categoryRepo) implements PostService {
    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(() ->
                        new ResourceNotFound("user", "user_id", userId));
        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(() ->
                        new ResourceNotFound("category", "categoryId", categoryId));
        Post post = this.dtoToEntity(postDto);
        post.setImageName("default.img");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        post = postRepo.save(post);
        return this.entityToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() ->
                        new ResourceNotFound("post", "id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDate(new Date());
        post = postRepo.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() ->
                        new ResourceNotFound("post", "postId", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() ->
                        new ResourceNotFound("post", "postId", postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepo.findAll();
        return posts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByUser(Long user_id) {
        User user = this.userRepo.findById(user_id).
                orElseThrow(() ->
                        new ResourceNotFound("user", "user_id", user_id));
        List<Post> posts = postRepo.findByUser(user);
        return posts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByCategory(Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(() ->
                        new ResourceNotFound("category", "categoryId", categoryId));
        List<Post> posts = postRepo.findByCategory(category);
        return posts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    private Post dtoToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto entityToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        return postDto;
    }
}
