package com.damian.uas.endpoints;

import com.damian.uas.hello.DemoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/hotels")
@RestController
@CrossOrigin

public class HotelController {
    @Autowired
    private DemoInterface demoInterface;

    @GetMapping("/getFuck")
    public String getFuck(){
       return demoInterface.getFuck();

    }

}
