package com.damian.ps.config;

import com.damian.ps.interfaces.JWTInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class FilterConfig {
    @Autowired
    private final JWTInterface jwtInterface;
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;
    public FilterConfig(JWTInterface jwtInterface) {
        this.jwtInterface = jwtInterface;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(new ReqFilter(jwtInterface, handlerExceptionResolver), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}