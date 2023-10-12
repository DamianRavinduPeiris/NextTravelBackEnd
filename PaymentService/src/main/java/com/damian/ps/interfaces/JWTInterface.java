package com.damian.ps.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service")

public interface JWTInterface {
    @GetMapping(path = "/isAuthenticated")
     String isAuthenticated(String jwtToken);

}
