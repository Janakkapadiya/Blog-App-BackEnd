package com.blogapp.blog.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long user_id;
    private String name;
    private String email;
    private String password;
    private String about;
}
