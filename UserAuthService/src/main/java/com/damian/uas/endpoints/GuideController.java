package com.damian.uas.endpoints;

import com.damian.uas.dto.GuideDTO;
import com.damian.uas.hello.DemoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/guide")
@CrossOrigin
public class GuideController {
    @Autowired
    private DemoInterface demoInterface;

    @PostMapping(path = "/getGuide", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGuideInfo(@RequestBody GuideDTO guideDTO) {
        return demoInterface.getGuide(guideDTO);
    }

    @GetMapping(path = "/paramTester", params = {"param1", "param2"})
    public String getGuide(@RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        return demoInterface.paramTester(param1, param2);
    }
}
