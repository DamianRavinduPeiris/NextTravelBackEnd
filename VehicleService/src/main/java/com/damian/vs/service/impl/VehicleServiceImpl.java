package com.damian.vs.service.impl;

import com.damian.vs.dto.VehicleDTO;
import com.damian.vs.repo.VehicleRepo;
import com.damian.vs.response.Response;
import com.damian.vs.service.custom.VehicleService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private Response response;
    @Autowired
    private ModelMapper Mapper;
    @Override
    public ResponseEntity<Response> add(VehicleDTO vehicleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Response> update(VehicleDTO vehicleDTO) {
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
    public ResponseEntity<Response> createAndSendResponse(int statusCode, String msg, Object data) {
        return null;
    }
}
