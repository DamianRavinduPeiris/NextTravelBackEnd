package com.damian.ps.service.impl;

import com.damian.ps.dto.PaymentsDTO;
import com.damian.ps.entity.Payments;
import com.damian.ps.interfaces.PackageDetailsInterface;
import com.damian.ps.interfaces.UserInterface;
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
    @Autowired
    private UserInterface userInterface;
    @Autowired
    private PackageDetailsInterface packageDetailsInterface;

    @Override
    public ResponseEntity<Response> add(PaymentsDTO paymentsDTO) {
        if (search(paymentsDTO.getPaymentId()).getBody().getData() == null) {
            paymentsDTO.setPaymentImageLocation(paymentsDTO.getPaymentImageLocation().replace("\\", "/"));
            paymentsRepo.save(mapper.map(paymentsDTO, Payments.class));
            return createAndSendResponse(HttpStatus.CREATED.value(), "Payment Done!", null);


        }
        return createAndSendResponse(HttpStatus.CONFLICT.value(), "Payment already exists!", null);
    }

    @Override
    public ResponseEntity<Response> update(PaymentsDTO paymentsDTO) {
        if (search(paymentsDTO.getPaymentId()).getBody().getData() != null) {
            paymentsDTO.setPaymentImageLocation(paymentsDTO.getPaymentImageLocation().replace("\\", "/"));
            paymentsRepo.save(mapper.map(paymentsDTO, Payments.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Payment updated!", null);
        }


        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Payment not found!", null);
    }

    @Override
    public ResponseEntity<Response> search(String s) {
        Optional<Payments> payments = paymentsRepo.findById(s);
        System.out.println("PID : "+s);
        System.out.println(payments.toString());
        if (payments.isPresent()) {
            return createAndSendResponse(HttpStatus.OK.value(), "Payment found", mapper.map(payments.get(), PaymentsDTO.class));

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Payment not found", null);
    }

    @Override
    public ResponseEntity<Response> delete(String s,String uId,String pdId) {
        if (search(s).getBody().getData() != null) {
            paymentsRepo.deleteById(s);
            userInterface.deletePaymentsID(uId,s);
            userInterface.deletePID(uId,pdId);
            packageDetailsInterface.deletePD(pdId);
            return createAndSendResponse(HttpStatus.OK.value(), "Payment successfully deleted!", null);
        }


        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Payment not found!", null);
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
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Payments not found!", null);
    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int code, String message, Object data) {
        response.setStatusCode(code);
        response.setMessage(message);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.valueOf(code));
    }

    @Override
    public String findByPackageDetailsId(String packageDetailsId) {
        return paymentsRepo.findByPackageDetailsId(packageDetailsId).getPaymentId();
    }

    @Override
    public ResponseEntity<Response> deletePayment(String pID) {
        if(paymentsRepo.findById(pID).isEmpty()){
            return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Payment Not Found!",null);

        }
        paymentsRepo.deleteById(pID);
        return createAndSendResponse(HttpStatus.OK.value(), "Payment Deleted Successfully!",null);
    }

    @Override
    public ResponseEntity<Response> deletePaymentsByUser(String userId) {
        List<Payments> payments = paymentsRepo.findByUserId(userId);
        if(payments.isEmpty()){
            return createAndSendResponse(HttpStatus.OK.value(), "Payments Not Found!",null);

        }
        paymentsRepo.deleteAll(payments);
        return createAndSendResponse(HttpStatus.OK.value(), "Payments Deleted Successfully!",null);
    }
}
