package com.damian.pds.service.impl;

import com.damian.pds.dto.PackageDetailsDTO;
import com.damian.pds.repo.PackageDetailsRepo;
import com.damian.pds.response.Response;
import com.damian.pds.service.custom.PackageDetailsService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PackageDetailsServiceImpl implements PackageDetailsService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private Response response;
    @Autowired
    private PackageDetailsRepo packageDetailsRepo;

    @Override
    public ResponseEntity<Response> add(PackageDetailsDTO packageDetailsDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Response> update(PackageDetailsDTO packageDetailsDTO) {
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
        response.setStatusCode(statusCode);
        response.setMessage(msg);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(statusCode));
    }
}
