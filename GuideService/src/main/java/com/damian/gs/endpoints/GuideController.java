package com.damian.gs.endpoints;

import com.damian.gs.dto.GuideDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8080")
public class GuideController {
    @PostMapping(path = "/test",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGuide(@RequestBody GuideDTO guideDTO){
        Gson gson = new Gson();
        return gson.toJson(guideDTO);
    }


    @GetMapping(path = "/hello",params = {"param1","param2"})
    public String paramTester(@RequestParam("param1") String param1,@RequestParam("param2") String param2){

        return "Revived params: "+param1+" "+param2;
    }
}
