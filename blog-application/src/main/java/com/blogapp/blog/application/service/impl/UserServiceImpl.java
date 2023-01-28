package com.blogapp.blog.application.service.impl;

import com.blogapp.blog.application.dto.UserDto;
import com.blogapp.blog.application.entity.User;
import com.blogapp.blog.application.repo.UserRepo;
import com.blogapp.blog.application.service.UserService;
import exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        user = userRepo.save(user);
        return this.entityToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(() -> new ResourceNotFound("user","id",user_id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user = this.userRepo.save(user);
        return this.entityToDto(user);
    }

    @Override
    public UserDto getUserById(Long user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(() -> new ResourceNotFound("user","id",user_id));
        return this.entityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long user_id) {
       User user = this.userRepo.findById(user_id).orElseThrow(() -> new ResourceNotFound("user","id",user_id));
       this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto)
    {
        User user = modelMapper.map(userDto,User.class);
        user.setUser_id(userDto.getUser_id());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
//        user.setRole(userDto.getRole());
        return user;
    }

    private UserDto entityToDto(User user)
    {
        UserDto userDto = modelMapper.map(user,UserDto.class);
        userDto.setUser_id(user.getUser_id());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
//        userDto.setRole(user.getRole());
        return userDto;

    }
}
