package com.blogapp.blog.application.controller;

import com.blogapp.blog.application.dto.PostDto;
import com.blogapp.blog.application.service.PostService;
import exception.DeleteApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/")
public record PostController(PostService postService) {
    @PostMapping("/user/{user_id}/category/{categoryId}/createPost")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@Valid @RequestBody PostDto postDto, @PathVariable(value = "user_id") Long user_id,
                              @PathVariable(value = "categoryId") Long categoryId)
    {
        return postService.createPost(postDto,user_id,categoryId);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(value = "postId") Long postId)
    {
        return postService.updatePost(postDto,postId);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable(value = "postId") Long postId)
    {
        return postService.getPostById(postId);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<DeleteApiResponse> deletePost(@PathVariable(value = "postId") Long postId)
    {
        postService.deletePost(postId);
        return new ResponseEntity<>(new DeleteApiResponse("post delete successfully"),HttpStatus.OK);
    }

    @GetMapping("/")
    public List<PostDto> getAllPosts()
    {
        return postService.getAllPost();
    }
}
