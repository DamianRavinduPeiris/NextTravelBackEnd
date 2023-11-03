package com.damian.ss.endpoints;

import com.damian.ss.entity.ChargeRequest;
import com.damian.ss.entity.ChargeResponse;
import com.damian.ss.service.StripeService;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping()
@CrossOrigin
public class StripeController {

    @Autowired
    private StripeService paymentsService;
    @Autowired
    private ChargeResponse chargeResponse;

    @PostMapping(path = "/charge")
    public String charge(ChargeRequest chargeRequest, Model model){
        System.out.println("Amount : "+chargeRequest.getAmount());
        try {
            chargeRequest.setDescription("NextTravel Bookings - Damian Peiris.");
            chargeRequest.setCurrency(ChargeRequest.Currency.LKR);



            chargeRequest.setAmount(chargeRequest.getAmount()*100);
            Charge charge = paymentsService.charge(chargeRequest);
            model.addAttribute("id", charge.getId());
            model.addAttribute("status", charge.getStatus());
            model.addAttribute("chargeId", charge.getId());
            model.addAttribute("date", LocalDateTime.now());
            model.addAttribute("email",chargeRequest.getStripeEmail());
            model.addAttribute("amount",chargeRequest.getAmount());
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
