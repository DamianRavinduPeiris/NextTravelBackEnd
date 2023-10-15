package com.damian.packageservice.service.custom;

import com.damian.packageservice.dto.PackagesDTO;
import com.damian.packageservice.dto.superdto.SuperDTO;
import com.damian.packageservice.response.Response;
import com.damian.packageservice.service.SuperService;
import org.springframework.http.ResponseEntity;

public interface PackageService extends SuperService<PackagesDTO,String> {
    ResponseEntity<Response>addHotel(String packageID,String hotelID);
    ResponseEntity<Response>deleteHotel(String packageID,String hotelID);
    ResponseEntity<Response>addVehicle(String packageID,String vehicleID);
    ResponseEntity<Response>deleteVehicle(String packageID,String vehicleID);
}
