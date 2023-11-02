package com.damian.ps.service.custom;

import com.damian.ps.dto.PaymentsDTO;
import com.damian.ps.entity.Payments;
import com.damian.ps.response.Response;
import com.damian.ps.service.SuperService;
import org.springframework.http.ResponseEntity;

public interface PaymentService extends SuperService<PaymentsDTO,String> {
    String findByPackageDetailsId(String packageDetailsId);
    ResponseEntity<Response>deletePayment(String pID);
    ResponseEntity<Response>deletePaymentsByUser(String userId);
}
