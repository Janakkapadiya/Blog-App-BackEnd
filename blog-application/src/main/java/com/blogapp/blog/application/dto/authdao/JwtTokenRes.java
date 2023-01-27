package com.blogapp.blog.application.dto.authdao;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenRes {
    private String token;
}
