package com.damian.uas.hello;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "HOTEL-SERVICE")

public interface DemoInterface {
    @GetMapping(path = "/returnFuck")
     String getFuck();


}
