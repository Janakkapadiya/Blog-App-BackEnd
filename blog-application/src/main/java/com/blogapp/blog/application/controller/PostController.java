package com.blogapp.blog.application.controller;

import com.blogapp.blog.application.dto.PageDto;
import com.blogapp.blog.application.dto.PostDto;
import com.blogapp.blog.application.service.PostService;
import exception.DeleteApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public record PostController(PostService postService) {
    @PostMapping("/post/user/{user_id}/category/{categoryId}/createPost")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody PostDto postDto, @PathVariable(value = "user_id") Long user_id,
                              @PathVariable(value = "categoryId") Long categoryId)
    {
        return postService.createPost(postDto,user_id,categoryId);
    }

    @PutMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(value = "postId") Long postId)
    {
        return postService.updatePost(postDto,postId);
    }

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable(value = "postId") Long postId)
    {
        return postService.getPostById(postId);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<DeleteApiResponse> deletePost(@PathVariable(value = "postId") Long postId)
    {
        postService.deletePost(postId);
        return new ResponseEntity<>(new DeleteApiResponse("post delete successfully"),HttpStatus.OK);
    }

    @GetMapping("/post")
    public PageDto getAllPosts(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                               @RequestParam(value = "pageSize",defaultValue = "10",required = false  ) Integer pageSize,
                               @RequestParam(value = "sort",defaultValue = "postId",required = false) String sortBy)
    {
        return postService.getAllPost(pageNumber,pageSize,sortBy);
    }

    @GetMapping("/post/user/{user_id}")
    public List<PostDto> getPostByUser(@PathVariable(value = "user_id") Long user_id)
    {
        return this.postService.getAllPostByUser(user_id);
    }

    @GetMapping("/post/category/{categoryId}")
    public List<PostDto> getPostByCategory(@PathVariable(value = "categoryId") Long categoryId)
    {
        return this.postService.getAllPostByCategory(categoryId);
    }

}
