package com.damian.vs.service.custom;

import com.damian.vs.dto.VehicleDTO;
import com.damian.vs.response.Response;
import com.damian.vs.service.SuperService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService extends SuperService<VehicleDTO,String> {
    ResponseEntity<Response>deleteAllVehicles(List<String>vehiclesIDList);
}
