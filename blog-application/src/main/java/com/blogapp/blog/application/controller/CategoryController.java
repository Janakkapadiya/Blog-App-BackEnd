package com.blogapp.blog.application.controller;

import com.blogapp.blog.application.dto.CategoryDto;
import com.blogapp.blog.application.service.CategoryService;
import exception.DeleteApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public record CategoryController(CategoryService categoryService) {

    @PostMapping("/createCategory")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto)
    {
        return this.categoryService.createCategory(categoryDto);
    }

    @PutMapping("/updateCategory/{categoryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable(required = false,value = "categoryId") Long categoryId)
    {
        return this.categoryService.updateCategory(categoryDto,categoryId);
    }

    @GetMapping("/getCategoryById/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(Long categoryId)
    {
        return this.categoryService.getCategoryById(categoryId);
    }
    @GetMapping("/getAllCategory")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAllCategory()
    {
        return this.categoryService.getAllCategory();
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<DeleteApiResponse> deleteCategory(@PathVariable(value = "categoryId") Long categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new DeleteApiResponse("category deleted successfully"),HttpStatus.OK);
    }
}
