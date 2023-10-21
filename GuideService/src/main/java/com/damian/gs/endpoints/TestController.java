package com.damian.gs.endpoints;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guideTest")
@CrossOrigin
public class TestController {
    @GetMapping(path = "/test")
    public String test(){
        return "This is GuideService.";
    }
}
