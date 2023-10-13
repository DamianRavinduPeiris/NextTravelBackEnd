package com.damian.hs.endpoints;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hotel")
@CrossOrigin
public class HotelController {
    @GetMapping(path = "/test")
    public String getGuide() {
        return "This is Hotel!";
    }
}
