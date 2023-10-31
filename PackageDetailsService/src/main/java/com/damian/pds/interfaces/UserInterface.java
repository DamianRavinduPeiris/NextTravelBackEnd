package com.damian.pds.interfaces;

import com.damian.pds.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("auth-service")
public interface UserInterface {
    @PostMapping(path = "api/v1/user/updatePId",produces = MediaType.APPLICATION_JSON_VALUE,params = {"uId","pId"})
    ResponseEntity<Response> updatePackageDetailsID(@RequestParam("uId") String uId, @RequestParam("pId") String pId);
    @DeleteMapping(path ="api/v1/user/deletePID",params = {"uID","pID"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePID(@RequestParam("uID") String uID,@RequestParam("pID") String pID);

    @PostMapping(path = "api/v1/user/updatePaymentsID",produces = MediaType.APPLICATION_JSON_VALUE,params = {"uId","pId"})
    public ResponseEntity<Response>updatePaymentsID(@RequestParam("uId") String uId,@RequestParam("pId") String pId);
    @DeleteMapping(path ="api/v1/user/deletePaymentsID",params = {"uID","pID"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePaymentsID(@RequestParam("uID") String uID,@RequestParam("pID") String pID);
}
