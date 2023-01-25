package com.blogapp.blog.application.repo;

import com.blogapp.blog.application.entity.Category;
import com.blogapp.blog.application.entity.Post;
import com.blogapp.blog.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long>{
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
