package com.damian.ps.endpoints;

import com.damian.ps.dto.PaymentsDTO;
import com.damian.ps.response.Response;
import com.damian.ps.service.custom.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class PaymentsController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping(path = "/savePayment",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>savePayment(@RequestBody PaymentsDTO paymentsDTO){
        return paymentService.add(paymentsDTO);

    }
    @PutMapping(path = "/updatePayment",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>updatePayment(@RequestBody PaymentsDTO paymentsDTO){
        return paymentService.update(paymentsDTO);

    }
    @DeleteMapping(path = "/deletePayment",params = {"paymentID","userID","pdID"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePayment(@RequestParam("paymentID") String paymentID,@RequestParam("userID") String userID,@RequestParam("pdID") String pdID){
        return paymentService.delete(paymentID,userID,pdID);

    }

    @GetMapping(path = "/searchPayment",params = "paymentID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>savePayment(@RequestParam("paymentID") String paymentID){
        return paymentService.search(paymentID);

    }
    @GetMapping(path = "/getAllPayments",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getAllPayments(){
        return paymentService.getAll();

    }
    @GetMapping(path = "/findByPID",params = "pID")
    public String getPaymentBypID(@RequestParam("pID")String pid){
        return paymentService.findByPackageDetailsId(pid);

    }
    @DeleteMapping(path = "/deletePaymentOnly",params = "pID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePaymentOnly(@RequestParam("pID") String pID){
        return paymentService.deletePayment(pID);

    }
}
