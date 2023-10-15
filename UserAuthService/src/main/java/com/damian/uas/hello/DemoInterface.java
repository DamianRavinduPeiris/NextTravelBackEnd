package com.damian.uas.hello;


import com.damian.uas.dto.GuideDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "GUIDE-SERVICE")


public interface DemoInterface {

    @PostMapping(path = "/test",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGuide(@RequestBody  GuideDTO guideDTO);
    @GetMapping(path = "/hello",params = {"param1","param2"})
    public String paramTester(@RequestParam("param1") String param1,@RequestParam("param2") String param2);



}
