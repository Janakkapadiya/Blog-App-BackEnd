package com.blogapp.blog.application.controller;

import com.blogapp.blog.application.dto.UserDto;
import com.blogapp.blog.application.service.UserService;
import exception.DeleteApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController{
    public final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{user_id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable(required = false, value = "user_id") Long user_id)
    {
        UserDto updateUserDto = this.userService.updateUser(userDto,user_id);
        return new ResponseEntity<>(updateUserDto,HttpStatus.OK);
    }

    @GetMapping("/getUser/{user_id}")
    public ResponseEntity<UserDto> getUser(@PathVariable(value = "user_id") Long user_id)
    {
        UserDto getUserDto = this.userService.getUserById(user_id);
        return new ResponseEntity<>(getUserDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{user_id}")
    public ResponseEntity<DeleteApiResponse> deleteUser(@PathVariable(value = "user_id") Long user_id)
    {
        userService.deleteUserById(user_id);
        return new ResponseEntity<>(new DeleteApiResponse("deleted successfully"),HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
