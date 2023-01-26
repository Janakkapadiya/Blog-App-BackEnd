package com.blogapp.blog.application.service.impl;

import com.blogapp.blog.application.entity.User;
import com.blogapp.blog.application.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    public UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = this.userRepo.findByName(name);
        if(user == null)
        {
            throw new UsernameNotFoundException("user couldn't found");
        }else{
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
        }
    }
    public CustomUserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
