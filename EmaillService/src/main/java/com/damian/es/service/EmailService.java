package com.damian.es.service;

import com.damian.es.dto.EmailDetails;
import com.damian.es.entity.OTP;
import com.damian.es.service.custom.OTPService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String sendEmail(EmailDetails email) {
        String body = "Dear " + email.getName() + ",\n\n" + "Welcome aboard!.\n\n" + email.getBody() + "\n\n" + "Use the OTP below to complete your signup:\n" + generateAndSaveOtp(email.getToEmail()) + "\n\n" + "Regards,\n" + "Damian Peiris - Team NextTravel.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("drpeiris3.edu@gmail.com");
        message.setTo(email.getToEmail());
        message.setText(body);
        message.setSubject(email.getSubject());
        mailSender.send(message);
        return "EmailDetails sent successfully";


    }

    private String generateAndSaveOtp(String email) {
        String otpCode = Generators.randomBasedGenerator().generate().toString();
        otp.setEmail(email);
        otp.setOtp(otpCode);
        otpService.saveOTP(otp);
        return otpCode;


    }

}




