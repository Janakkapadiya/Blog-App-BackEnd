package com.blogapp.blog.application.service.impl;

import com.blogapp.blog.application.dto.CategoryDto;
import com.blogapp.blog.application.entity.Category;
import com.blogapp.blog.application.repo.CategoryRepo;
import com.blogapp.blog.application.service.CategoryService;
import exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CategoryServiceImpl(ModelMapper modelMapper,CategoryRepo categoryRepo) implements CategoryService {
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToEntity(categoryDto);
        category = categoryRepo.save(category);
        return this.entityToDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category", "id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setDescription(categoryDto.getDescription());
        category = this.categoryRepo.save(category);
        return this.entityToDto(category);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category", "id", categoryId));
        return this.entityToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category = this.categoryRepo.findAll();
        return category.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category", "id", categoryId));
        this.categoryRepo.delete(category);
    }

    private Category dtoToEntity(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    private CategoryDto entityToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
}
