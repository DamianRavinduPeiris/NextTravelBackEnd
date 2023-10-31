package com.damian.ps.service.custom;

import com.damian.ps.dto.PaymentsDTO;
import com.damian.ps.entity.Payments;
import com.damian.ps.service.SuperService;

public interface PaymentService extends SuperService<PaymentsDTO,String> {
    String findByPackageDetailsId(String packageDetailsId);
}
