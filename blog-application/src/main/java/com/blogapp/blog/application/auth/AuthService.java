package com.blogapp.blog.application.auth;

import com.blogapp.blog.application.config.JwtUtil;
import com.blogapp.blog.application.dto.authdao.JwtTokenReq;
import com.blogapp.blog.application.dto.authdao.JwtTokenRes;
import com.blogapp.blog.application.dto.authdao.RegisterReq;
import com.blogapp.blog.application.entity.User;
import com.blogapp.blog.application.repo.UserRepo;
import exception.ErrorResponseHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public JwtTokenRes register(RegisterReq registerReq)
    {
        User user = User.
                builder().
                name(registerReq.getName()).
                email(registerReq.getEmail()).
                password(passwordEncoder.encode(registerReq.getPassword())).
                build();
        userRepo.save(user);
        String jwtToken = jwtUtil.generateToken(user);
        return JwtTokenRes.builder().token(jwtToken).build();
    }

    public JwtTokenRes authenticate(JwtTokenReq jwtTokenReq) throws ErrorResponseHandle {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtTokenReq.getEmail(),
                jwtTokenReq.getPassword()
        ));
        User user = userRepo.findByEmail(jwtTokenReq.getEmail()).orElseThrow(() -> new ErrorResponseHandle(401,"error"));
        String jwtToken = jwtUtil.generateToken(user);
        return JwtTokenRes.builder()
                .token(jwtToken)
                .build();
    }
}
