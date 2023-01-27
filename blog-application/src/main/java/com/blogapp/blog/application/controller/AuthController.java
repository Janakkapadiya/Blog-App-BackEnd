package com.blogapp.blog.application.controller;

import com.blogapp.blog.application.auth.AuthService;
import com.blogapp.blog.application.dto.authdao.RegisterReq;
import com.blogapp.blog.application.dto.authdao.JwtTokenReq;
import com.blogapp.blog.application.dto.authdao.JwtTokenRes;
import exception.ErrorResponseHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<JwtTokenRes> signUp(@RequestBody RegisterReq registerReq)
    {
        JwtTokenRes register = authService.register(registerReq);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtTokenRes> signIn(@RequestBody JwtTokenReq jwtTokenReq) throws ErrorResponseHandle {
        JwtTokenRes authenticate = authService.authenticate(jwtTokenReq);
        return new ResponseEntity<>(authenticate,HttpStatus.OK);
    }
}
