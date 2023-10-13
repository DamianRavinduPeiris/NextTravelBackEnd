package com.damian.ps.endpoints;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
    @GetMapping(path = "/hello")
    public String hello() {
        return " yay";
    }
}



