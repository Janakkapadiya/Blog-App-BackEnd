package com.blogapp.blog.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;
    @NotBlank
    private String categoryTitle;
    @NotBlank
    private String description;
}
