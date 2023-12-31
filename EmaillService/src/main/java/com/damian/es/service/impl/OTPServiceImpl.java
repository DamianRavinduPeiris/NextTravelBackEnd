package com.damian.es.service.impl;

import com.damian.es.entity.OTP;
import com.damian.es.repo.OTPRepo;
import com.damian.es.response.Response;
import com.damian.es.service.custom.OTPService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class OTPServiceImpl implements OTPService {
    @Autowired
    private OTPRepo otpRepo;
    @Autowired
    private Response response;

    @Override
    public ResponseEntity<Response> getOTP(String s) {
        Optional<OTP> otpDetails = otpRepo.findByEmail(s);
        if (otpDetails.isPresent()) {
            return createAndSendResponse(HttpStatus.FOUND.value(), "OTP details found", otpDetails.get());

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "OTP details not found", null);


    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int statusCode, String message, Object data) {
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.valueOf(statusCode));
    }

    @Override
    public void saveOTP(OTP otp) {
        otpRepo.save(otp);


    }
}
