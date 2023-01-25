package com.blogapp.blog.application.service;

import com.blogapp.blog.application.dto.CategoryDto;

import java.util.List;

public interface CategoryService{
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

    CategoryDto getCategoryById(Long categoryId);

    List<CategoryDto> getAllCategory();

    void deleteCategory(Long categoryId);
}
