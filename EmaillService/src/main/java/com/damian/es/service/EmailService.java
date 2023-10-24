package com.damian.es.service;

import com.damian.es.dto.EmailDetails;
import com.damian.es.entity.OTP;
import com.damian.es.response.Response;
import com.damian.es.service.custom.OTPService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OTPService otpService;
    @Autowired
    private OTP otp;
    @Autowired
    private Response response;

    public ResponseEntity<Response> sendEmail(EmailDetails email) {
        System.out.println("Sending Email to: " + email.getToEmail());
        String otp = generateAndSaveOtp(email.getToEmail());
        String body = "Dear " + email.getName() + ",\n\n" + "Welcome aboard!.\n\n" +  "\n\n" + "Use the OTP below to complete your signup:\n" + otp + "\n\n" + "Regards,\n" + "Damian Peiris - Team NextTravel.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("drpeiris3.edu@gmail.com");
        message.setTo(email.getToEmail());
        message.setText(body);
        mailSender.send(message);
        response.setStatusCode(200);
        response.setMessage("Email sent successfully");
        response.setData(otp);
        return new ResponseEntity<>(response, org.springframework.http.HttpStatus.OK);


    }

    private String generateAndSaveOtp(String email) {
        String otpCode = Generators.randomBasedGenerator().generate().toString();
        otp.setEmail(email);
        otp.setOtp(otpCode);
        otpService.saveOTP(otp);
        return otpCode;


    }

}




