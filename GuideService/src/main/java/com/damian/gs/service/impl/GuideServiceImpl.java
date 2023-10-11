package com.damian.gs.service.impl;

import com.damian.gs.dto.GuideDTO;
import com.damian.gs.repo.GuideRepo;
import com.damian.gs.response.Response;
import com.damian.gs.service.custom.GuideService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GuideServiceImpl implements GuideService {
    @Autowired
    private GuideRepo guideRepo;
    @Autowired
    private Response response;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ResponseEntity<Response> add(GuideDTO guideDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Response> update(GuideDTO guideDTO) {
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
