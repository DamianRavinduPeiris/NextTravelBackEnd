package com.damian.uas.endpoints;

import com.damian.uas.hello.DemoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/guide")
@CrossOrigin
public class GuideController {
    @Autowired
    private DemoInterface demoInterface;

    @GetMapping(path = "/getGuide")
    public String getGuide(){
        return demoInterface.getGuide();
    }
}
