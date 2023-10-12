package com.damian.uas.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor

public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private final HandlerExceptionResolver handlerExceptionResolver;
    @Autowired
    private JWTService JWTService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        /*Handling re-directed requests. -Start.*/
        if (request.getHeader("jwtToken") != null) {
            String jwtToken = request.getHeader("jwtToken");
            System.out.println("JWT Header : "+jwtToken);
            System.out.println("Feign client : " + request.getParameter("jwtToken"));
            UserDetails user = userDetailsService.loadUserByUsername(JWTService.extractUsername(jwtToken));
            if (JWTService.validateToken(jwtToken, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                System.out.println("auth status: " + authToken.isAuthenticated());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                return;

            }

        }
        /*Handling re-directed requests. -End.*/
        System.out.println("This is JWTAuthFilter."+request.getHeader("Authorization"));
        String authHeader = request.getHeader("Authorization");//Extracting the header.
        String jwtToken = null;
        String userName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No token found! - This is UAS.");
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);


        try {
            userName = JWTService.extractUsername(jwtToken);
            System.out.println("Username : " + userName);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, new RuntimeException("Invalid token : " + exception.getLocalizedMessage()));
            return;

        }
        //Checking of the username's not nullability  and the authentication status of the current user.
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailsService.loadUserByUsername(userName);
            System.out.println("User : " + user.toString());

            if (JWTService.validateToken(jwtToken, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                System.out.println("auth status: " + authToken.isAuthenticated());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);


            }


        }
        filterChain.doFilter(request, response);


    }
}
