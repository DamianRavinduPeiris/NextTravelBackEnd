package com.damian.pds.interfaces;

import com.damian.pds.dto.PaymentsDTO;
import com.damian.pds.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("payment-service")
public interface PaymentsInterface {
    @PostMapping(path = "/savePayment",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> savePayment(@RequestBody PaymentsDTO paymentsDTO);
    @GetMapping(path = "/findByPID",params = "pID")
    public String getPaymentBypID(@RequestParam("pID")String pid);
    @PutMapping(path = "/updatePayment",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>updatePayment(@RequestBody PaymentsDTO paymentsDTO);
    @DeleteMapping(path = "/deletePayment",params = "paymentID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePayment(@RequestParam("paymentID") String paymentID);
}
