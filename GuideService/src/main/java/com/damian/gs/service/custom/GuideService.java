package com.damian.gs.service.custom;

import com.damian.gs.dto.GuideDTO;
import com.damian.gs.response.Response;
import com.damian.gs.service.SuperService;
import org.springframework.http.ResponseEntity;

public interface GuideService extends SuperService<GuideDTO,String> {
    ResponseEntity<Response>searchByGuideName(String guideName);
}
