package com.blogapp.blog.application.controller;

import com.blogapp.blog.application.config.JwtUtil;
import com.blogapp.blog.application.dto.JwtTokenRes;
import com.blogapp.blog.application.dto.JwtTokenReq;
import com.blogapp.blog.application.service.impl.CustomUserDetailsServiceImpl;
import exception.ErrorResponseHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AuthController {
    @Autowired
    public JwtUtil jwtUtil;
    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("userDetails")
    public CustomUserDetailsServiceImpl customUserDetailsService;

    @PostMapping("/signIn")
    public ResponseEntity<JwtTokenRes> createJwtToken(@RequestBody JwtTokenReq jwtTokenRes) throws ErrorResponseHandle {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtTokenRes.getName(), jwtTokenRes.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.getStackTrace();
            throw new ErrorResponseHandle(400,"Bed credentials");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtTokenRes.getName());

        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtTokenRes(token));
    }
}
