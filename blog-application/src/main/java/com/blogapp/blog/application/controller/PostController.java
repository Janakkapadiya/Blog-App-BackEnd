package com.blogapp.blog.application.controller;

import com.blogapp.blog.application.dto.PageDto;
import com.blogapp.blog.application.dto.PostDto;
import com.blogapp.blog.application.service.ImageUploadService;
import com.blogapp.blog.application.service.PostService;
import com.blogapp.blog.application.utilitys.PageIndexes;
import exception.DeleteApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    public final PostService postService;
    public final ImageUploadService imageUploadService;
    @Value("${project.image}")
    private String path;

    public PostController(PostService postService, ImageUploadService imageUploadService) {
        this.postService = postService;
        this.imageUploadService = imageUploadService;
    }

    @PostMapping("/post/user/{user_id}/category/{categoryId}/createPost")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody PostDto postDto, @PathVariable(value = "user_id") Long user_id,
                              @PathVariable(value = "categoryId") Long categoryId) {
        return postService.createPost(postDto, user_id, categoryId);
    }

    @PutMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(value = "postId") Long postId) {
        return postService.updatePost(postDto, postId);
    }

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable(value = "postId") Long postId) {
        return postService.getPostById(postId);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<DeleteApiResponse> deletePost(@PathVariable(value = "postId") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new DeleteApiResponse("post delete successfully"), HttpStatus.OK);
    }

    @GetMapping("/post")
    public PageDto getAllPosts(@RequestParam(value = "pageNumber", defaultValue = PageIndexes.PAGE_NUMBER, required = false) Integer pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = PageIndexes.PAGE_SIZE, required = false) Integer pageSize,
                               @RequestParam(value = "sort", defaultValue = PageIndexes.CURRENT_SORT_BY, required = false) String sortBy) {
        return postService.getAllPost(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/post/user/{user_id}")
    public List<PostDto> getPostByUser(@PathVariable(value = "user_id") Long user_id) {
        return this.postService.getAllPostByUser(user_id);
    }

    @GetMapping("/post/category/{categoryId}")
    public List<PostDto> getPostByCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return this.postService.getAllPostByCategory(categoryId);
    }

    @GetMapping("/post/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> searchPost(@RequestParam(value = "keyword") String keyword) {
        return this.postService.searchByKeyWord(keyword);
    }

    //image uploading
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> fileUpload(@RequestParam("image") MultipartFile image, @PathVariable(value = "postId") Long postId) throws IOException {
        String filename = this.imageUploadService.uploadImage(path, image);
        PostDto postDto = this.postService.getPostById(postId);
        postDto.setImageName(filename);
        PostDto post = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //image serving
    @GetMapping(value = "post/image/{fileName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void loadFileAsResource(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        InputStream resource = this.imageUploadService.loadFileAsResource(path,fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
