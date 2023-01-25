package com.blogapp.blog.application.repo;

import com.blogapp.blog.application.entity.Category;
import com.blogapp.blog.application.entity.Post;
import com.blogapp.blog.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long>{
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    @Query("SELECT p FROM Post p WHERE " +
            "p.title LIKE CONCAT('%',:keyword, '%')" +
            "Or p.content LIKE CONCAT('%', :keyword, '%')")
    List<Post> findByTitleContaining(String keyword);
}
