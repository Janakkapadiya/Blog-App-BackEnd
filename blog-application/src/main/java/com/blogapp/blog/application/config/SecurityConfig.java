package com.blogapp.blog.application.config;

import com.blogapp.blog.application.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public final String[] ALLOWED_URLS = {
            "*/api/signIn",
            "*/*"
    };
    public final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    public final JwtFilter jwtFilter;
    public final UserRepo userRepo;
    @Autowired
    @Qualifier("userDetails")
    public UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtFilter jwtFilter, UserRepo userRepo) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtFilter = jwtFilter;
        this.userRepo = userRepo;
    }

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpRequest) throws Exception {
        httpRequest.
                csrf().
                disable().
                authorizeHttpRequests().
                anyRequest().
                authenticated()
                .and()
                .exceptionHandling().
                authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpRequest.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpRequest.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/api/signIn");
    }

    @Bean
    public AuthenticationManager configureAuthenticationManager() {
        return CustomAuthenticationManager.builder().userDetailsService(userDetailsService).passwordEncoder(passwordEncoder).build();
    }
}
