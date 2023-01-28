package com.blogapp.blog.application.repo;

import com.blogapp.blog.application.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Role, Long> {

}
