package com.blogapp.blog.application.dto.authdao;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenReq {
    private String email;
    private String password;
}
