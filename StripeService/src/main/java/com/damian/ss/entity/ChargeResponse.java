package com.damian.ss.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ChargeResponse {
    private String chargeId;
    private String chargeStatus;
    private String chargeBalanceTransaction;
    private String chargeDescription;
    private String chargeCurrency;
    private String chargeAmount;

}
