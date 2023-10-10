package com.damian.hs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fuck")
public class FuckController {
    @GetMapping(path = "/hello")
    public String getHello(){
        return "HELLO!";
    }
}
