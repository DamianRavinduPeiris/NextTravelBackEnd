package com.damian.uas.interfaces;

import com.damian.uas.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("payment-service")
public interface PaymentsInterface {
    @DeleteMapping(path = "/deletePaymentByUser",params = "userId",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deletePaymentsByUser(@RequestParam("userId") String userId);
}
