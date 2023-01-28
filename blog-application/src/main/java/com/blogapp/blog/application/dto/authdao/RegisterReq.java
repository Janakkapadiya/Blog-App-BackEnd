package com.blogapp.blog.application.dto.authdao;

import com.blogapp.blog.application.enums.Role;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterReq {
   private String name;
   private String email;
   private String password;
   private Role role;
}
