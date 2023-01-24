package com.blogapp.blog.application.repo;

import com.blogapp.blog.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepo extends JpaRepository<User,Long> {

}
