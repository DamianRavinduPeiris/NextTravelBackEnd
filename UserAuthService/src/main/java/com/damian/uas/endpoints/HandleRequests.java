package com.damian.uas.endpoints;

import com.damian.uas.config.JWTService;
import com.damian.uas.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class HandleRequests {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @GetMapping(path = "/isAuthenticated",params = "jwtToken")
    public  String isAuthenticated( @RequestParam("jwtToken") String jwtToken){
        System.out.println("jwtToken = " + jwtToken);
        UserDetails user = userDetailsService.loadUserByUsername(jwtService.extractUsername(jwtToken));
        if(jwtService.validateToken(jwtToken,user))return "true";
        else return "false";


    }
}
