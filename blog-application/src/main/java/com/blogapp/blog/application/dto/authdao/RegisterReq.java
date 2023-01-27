package com.blogapp.blog.application.dto.authdao;

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
