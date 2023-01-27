package com.blogapp.blog.application.auth;

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
}
