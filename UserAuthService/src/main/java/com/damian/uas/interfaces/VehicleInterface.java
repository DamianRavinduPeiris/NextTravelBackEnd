package com.damian.uas.interfaces;

import com.damian.uas.dto.VehicleDTO;
import com.damian.uas.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("vehicle-service")
public interface VehicleInterface {
    @PostMapping(path = "/saveVehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addVehicle(@RequestBody VehicleDTO vehicleDTO);
    @PutMapping(path = "/updateVehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateVehicle(@RequestBody VehicleDTO vehicleDTO);
    @GetMapping(path = "/searchVehicle", produces = MediaType.APPLICATION_JSON_VALUE, params = "vehicleID")
    public ResponseEntity<Response> searchVehicle(@RequestParam("vehicleID") String vehicleID);
    @DeleteMapping(path = "/deleteVehicle", produces = MediaType.APPLICATION_JSON_VALUE, params = "vehicleID")
    public ResponseEntity<Response> deleteVehicle(@RequestParam("vehicleID") String vehicleID);
    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAllVehicles();
    @GetMapping(path = "/getVehicleByBrand",params = "/vehicleBrand",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getVehicleByBrand(@RequestParam("vehicleBrand")String vehicleBrand);
}
