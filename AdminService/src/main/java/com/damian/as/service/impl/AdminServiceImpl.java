package com.damian.as.service.impl;

import com.damian.as.dto.AdminDTO;
import com.damian.as.response.Response;
import com.damian.as.service.custom.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Override
    public ResponseEntity<Response> add(AdminDTO adminDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Response> update(AdminDTO adminDTO) {
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
    public ResponseEntity<Response> createAndSendResponse(int code, String message, Object object) {
        return null;
    }
}
