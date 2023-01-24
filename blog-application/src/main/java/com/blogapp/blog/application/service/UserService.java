package com.blogapp.blog.application.service;

import com.blogapp.blog.application.dto.UserDto;

import java.util.List;
public interface UserService {
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Long user_id);

    UserDto getUserById(Long user_id);

    List<UserDto> getAllUsers();

    void deleteUserById(Long user_id);
}
