package com.damian.gs.endpoints;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@CrossOrigin
public class GuideController {
    @GetMapping(path = "/test")
    public String getGuide() {
        return "This is the guide!";
    }
}
