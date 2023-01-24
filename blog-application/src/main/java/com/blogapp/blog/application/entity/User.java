package com.blogapp.blog.application.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false,insertable = true, updatable = true)
    private Long user_id;
    @Column(name = "user_name",nullable = false,length = 100)
    private String name;
    @Column(name = "user_email",nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String about;
}