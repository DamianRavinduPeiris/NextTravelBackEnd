package com.damian.ps.service.impl;

import com.damian.ps.dto.PaymentsDTO;
import com.damian.ps.repo.PaymentsRepo;
import com.damian.ps.response.Response;
import com.damian.ps.service.custom.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentsServiceImpl implements PaymentService {
    @Autowired
    private Response response;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PaymentsRepo paymentsRepo;
    @Override
    public ResponseEntity<Response> add(PaymentsDTO paymentsDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Response> update(PaymentsDTO paymentsDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Response> search(String s) {
        return null;
    }

    @Override
    public ResponseEntity<Response> delete(String s) {
        return null;
    }

    @Override
    public ResponseEntity<Response> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int code, String message, Object data) {
        return null;
    }
}
