package com.damian.ps.interfaces;

import com.damian.ps.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("packagedetails-service")
public interface PackageDetailsInterface {
    @DeleteMapping(path = "/deletePD",params = "pID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deletePD(@RequestParam("pID")String pID);
}
