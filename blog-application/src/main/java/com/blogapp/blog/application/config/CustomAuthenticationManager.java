package com.blogapp.blog.application.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Builder
@Getter
@Setter
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    @Qualifier("userDetails")
    public UserDetailsService userDetailsService;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName() + "";
        String password = authentication.getCredentials() + "";

        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (userDetails == null) {
            throw new BadCredentialsException("Bad Credentials!");
        }
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(name, null, null);
        } else {
            throw new BadCredentialsException("Bad Credentials!");
        }
    }
}
