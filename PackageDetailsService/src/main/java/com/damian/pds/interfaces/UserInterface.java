package com.damian.pds.interfaces;

import com.damian.pds.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("auth-service")
public interface UserInterface {
    @PutMapping(path = "/updatePId",produces = MediaType.APPLICATION_JSON_VALUE,params = {"uId","pId"})
    ResponseEntity<Response> updatePid(@RequestParam("uId") String uId, @RequestParam("pId") String pId);
}
