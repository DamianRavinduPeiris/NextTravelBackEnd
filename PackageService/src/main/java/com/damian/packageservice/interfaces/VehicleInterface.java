package com.damian.packageservice.interfaces;

import com.damian.packageservice.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("vehicle-service")
public interface VehicleInterface {
    @DeleteMapping(path = "/deleteAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAllVehicles(@RequestBody List<String> vehiclesIDList);
}
