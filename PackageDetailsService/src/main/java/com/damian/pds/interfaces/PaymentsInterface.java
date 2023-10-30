package com.damian.pds.interfaces;

import com.damian.pds.dto.PaymentsDTO;
import com.damian.pds.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient("payment-service")
public interface PaymentsInterface {
    @PostMapping(path = "/savePayment",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> savePayment(@RequestBody PaymentsDTO paymentsDTO);
}
