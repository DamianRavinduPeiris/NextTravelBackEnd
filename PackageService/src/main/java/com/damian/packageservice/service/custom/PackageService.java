package com.damian.packageservice.service.custom;

import com.damian.packageservice.dto.PackagesDTO;
import com.damian.packageservice.dto.superdto.SuperDTO;
import com.damian.packageservice.response.Response;
import com.damian.packageservice.service.SuperService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PackageService extends SuperService<PackagesDTO,String> {
    ResponseEntity<Response>addHotel(String packageID,String hotelID);
    ResponseEntity<Response>deleteHotel(String packageID,String hotelID);
    ResponseEntity<Response>addVehicle(String packageID,String vehicleID);
    ResponseEntity<Response>deleteVehicle(String packageID,String vehicleID);
    List<String> getHotelsList(String packageID);
    List<String>getVehiclesList(String packageID);
    List<String>getPackageIDs();
    ResponseEntity<Response> updateHotelPackageID(String oldPackageId,String newPackageId,String hotelId);
    ResponseEntity<Response> updateVehiclePackageID(String oldPackageId,String newPackageId,String vehicleId);
    ResponseEntity<Response>getPackageByCategory(String category);
}
