package com.damian.ps.config;

import com.damian.ps.interfaces.JWTInterface;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;


@Component


public class ReqFilter extends OncePerRequestFilter {


    private final HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public ReqFilter( HandlerExceptionResolver handlerExceptionResolver) {



        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("This is ReqFilter.This is the Auth header : " + authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;

        }
        /*Transferring all incoming requests to the UAS for Auth purposes.*/
        RestTemplate restTemplate = new RestTemplate();
        String redirectUrl = "http://localhost:8080/isAuthenticated?jwtToken=" + authHeader.substring(7);
        HttpHeaders httpHeaders = new HttpHeaders();
       httpHeaders.set("Authorization", "Bearer "+authHeader.substring(7));
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Boolean> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(redirectUrl, HttpMethod.GET, requestEntity, Boolean.class);
            System.out.println("Response from UAS : " + responseEntity.getBody());
            if(responseEntity.getBody().booleanValue()){
                filterChain.doFilter(request, response);

            }else{
                throw new RuntimeException("Invalid token!");
            }



        } catch (RestClientException e) {
            handlerExceptionResolver.resolveException(request, response, null,new RuntimeException("Invalid token : " + e.getLocalizedMessage()));
            return;
        }





    }
}
