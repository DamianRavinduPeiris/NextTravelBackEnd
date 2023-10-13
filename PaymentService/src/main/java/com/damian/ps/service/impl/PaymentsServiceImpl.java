package com.damian.ps.service.impl;

import com.damian.ps.dto.PaymentsDTO;
import com.damian.ps.entity.Payments;
import com.damian.ps.repo.PaymentsRepo;
import com.damian.ps.response.Response;
import com.damian.ps.service.custom.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (search(paymentsDTO.getPaymentId()).getBody().getData() == null) {
            paymentsRepo.save(mapper.map(paymentsDTO, Payments.class));
            return createAndSendResponse(HttpStatus.CREATED.value(), "Payment Done!", null);


        }
        throw new RuntimeException("Payment already exists");
    }

    @Override
    public ResponseEntity<Response> update(PaymentsDTO paymentsDTO) {
        if (search(paymentsDTO.getPaymentId()).getBody().getData() != null) {
            paymentsRepo.save(mapper.map(paymentsDTO, Payments.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Payment updated!", null);
        }


        throw new RuntimeException("Payment not found!");
    }

    @Override
    public ResponseEntity<Response> search(String s) {
        Optional<Payments> payments = paymentsRepo.findById(s);
        if (payments.isPresent()) {
            return createAndSendResponse(HttpStatus.OK.value(), "Payment found", mapper.map(payments.get(), PaymentsDTO.class));

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Payment not found", null);
    }

    @Override
    public ResponseEntity<Response> delete(String s) {
        if (search(s).getBody().getData() != null) {
            paymentsRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Payment successfully deleted!", null);
        }


        throw new RuntimeException("Payment not found!");
    }

    @Override
    public ResponseEntity<Response> getAll() {
        List<Payments> payments = paymentsRepo.findAll();
        if (!payments.isEmpty()) {
            List<PaymentsDTO> paymentsDTOS = new ArrayList<>();
            payments.forEach((p) -> {
                paymentsDTOS.add(mapper.map(p, PaymentsDTO.class));

            });
            return createAndSendResponse(HttpStatus.OK.value(), "Payments successfully retrieved!", paymentsDTOS);

        }
        throw new RuntimeException("No payments found!");
    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int code, String message, Object data) {
        response.setMessage(message);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.valueOf(code));
    }
}
