package com.damian.es.service;

import com.damian.es.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(Email email,String name) {
        String body = "Dear " + name + ",\n\n"
                + "Welcome aboard " + name + " to NextTravel.\n\n"
                + email.getBody() + "\n\n"
                + "Use the OTP below to complete your signup:\n"
                + otp + "\n\n"
                + "Regards,\n"
                + "Damian Peiris - Team NextTravel.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("drpeiris3.edu@gmail.com");
        message.setTo(email.getToEmail());
        message.setText(email.getBody());
        message.setSubject(email.getSubject());
        mailSender.send(message);
        return "Email sent successfully";




    }

}




