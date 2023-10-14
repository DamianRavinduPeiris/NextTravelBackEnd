package com.damian.uas.hello;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GUIDE-SERVICE")


public interface DemoInterface {

    @GetMapping(path = "/test")

    public String getGuide();



}
