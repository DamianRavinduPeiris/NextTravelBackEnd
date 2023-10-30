package com.damian.ss.endpoints;

import com.damian.ss.entity.ChargeRequest;
import com.damian.ss.entity.ChargeResponse;
import com.damian.ss.service.StripeService;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@CrossOrigin
public class StripeController {

    @Autowired
    private StripeService paymentsService;
    @Autowired
    private ChargeResponse chargeResponse;

    @PostMapping(path = "/charge")
    public String charge(ChargeRequest chargeRequest, Model model){
        try {
            chargeRequest.setDescription("NextTravel Bookings - Damian Peiris.");
            chargeRequest.setCurrency(ChargeRequest.Currency.LKR);
            Charge charge = paymentsService.charge(chargeRequest);
            model.addAttribute("id", charge.getId());
            model.addAttribute("status", charge.getStatus());
            model.addAttribute("chargeId", charge.getId());
            model.addAttribute("balance_transaction",
            charge.getBalanceTransaction());
            chargeResponse.setChargeId(charge.getId());
            chargeResponse.setChargeStatus(charge.getStatus());
            chargeResponse.setChargeBalanceTransaction(charge.getBalanceTransaction());
            chargeResponse.setChargeDescription(charge.getDescription());
            chargeResponse.setChargeCurrency(charge.getCurrency());
            chargeResponse.setChargeAmount(charge.getAmount().toString());

            return "Response";
        } catch (Exception e) {
            throw new RuntimeException("StripeService failed to charge card  : "+e.getLocalizedMessage());
        }

    }
}
