package com.damian.ps.endpoints;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {
    @PostMapping(path = "/hello", params = {"vehicleID", "packageID"})
    public String hello(@RequestParam("vehicleID")String vehicleID, @RequestParam("packageID")String packageID) {
        return " yay";
    }
}



