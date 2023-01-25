package com.blogapp.blog.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    @NotNull
    private Long categoryId;
    @NotBlank
    private String categoryTitle;
    @NotBlank
    private String description;
}
