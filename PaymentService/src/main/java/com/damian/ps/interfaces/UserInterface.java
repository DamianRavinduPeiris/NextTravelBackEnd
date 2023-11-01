package com.damian.ps.interfaces;

import com.damian.ps.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("auth-service")
public interface UserInterface {
    @DeleteMapping(path ="api/v1/user/deletePaymentsID",params = {"uID","pID"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePaymentsID(@RequestParam("uID") String uID,@RequestParam("pID") String pID);
    @DeleteMapping(path ="api/v1/user/deletePID",params = {"uID","pID"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePID(@RequestParam("uID") String uID,@RequestParam("pID") String pID);
}
