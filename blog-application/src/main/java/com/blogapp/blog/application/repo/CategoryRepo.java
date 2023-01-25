package com.blogapp.blog.application.repo;

import com.blogapp.blog.application.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long>{

}
