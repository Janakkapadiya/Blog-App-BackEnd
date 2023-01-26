package com.blogapp.blog.application.repo;

import com.blogapp.blog.application.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {

}
