package com.blogapp.blog.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Long postId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String imageName;
    private Date date;
    private CategoryDto category;
    private UserDto user;
}
