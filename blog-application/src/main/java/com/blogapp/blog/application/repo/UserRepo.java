package com.blogapp.blog.application.repo;

import com.blogapp.blog.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findUserByEmail(String email);

}
