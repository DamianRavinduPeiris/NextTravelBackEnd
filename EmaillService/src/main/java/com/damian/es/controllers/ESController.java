package com.damian.es.controllers;

import com.damian.es.dto.EmailDetails;
import com.damian.es.response.Response;
import com.damian.es.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class ESController {
    @Autowired
    private EmailService emailService;
    @PostMapping(path = "/sendEmail",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> sendEmail(@RequestBody EmailDetails email){
        System.out.println("Email Details: "+email.toString());
       return emailService.sendEmail(email);

    }
}
