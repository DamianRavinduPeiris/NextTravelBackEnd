package com.damian.vs.endpoints;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vehicles")
@CrossOrigin
public class GuideController {
    @GetMapping(path = "/test")
    public String getGuide() {
        return "This is VehicleService!";
    }
}
