package com.blogapp.blog.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @NotNull
    private Long user_id;
    @Email
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String about;
}
