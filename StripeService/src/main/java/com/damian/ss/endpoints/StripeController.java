package com.damian.ss.endpoints;

import com.damian.ss.entity.ChargeRequest;
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

    @PostMapping(path = "/charge")
    public String charge(ChargeRequest chargeRequest, Model model){
        try {
            chargeRequest.setDescription("Example charge");
            chargeRequest.setCurrency(ChargeRequest.Currency.LKR);
            Charge charge = paymentsService.charge(chargeRequest);
            model.addAttribute("id", charge.getId());
            model.addAttribute("status", charge.getStatus());
            model.addAttribute("chargeId", charge.getId());
            model.addAttribute("balance_transaction",
            charge.getBalanceTransaction());
            System.out.println("Charge Id : "+charge.getId());
            System.out.println("Charge Status : "+charge.getStatus());
            System.out.println("Charge Balance Transaction : "+charge.getBalanceTransaction());
            System.out.println("Charge Description : "+charge.getDescription());
            System.out.println("Charge Currency : "+charge.getCurrency());
            System.out.println("Charge Amount : "+charge.getAmount());
        } catch (Exception e) {
            throw new RuntimeException("StripeService failed to charge card  : "+e.getLocalizedMessage());
        }
        return "result";
    }
}
