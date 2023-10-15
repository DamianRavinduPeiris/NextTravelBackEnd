package com.damian.packageservice.endpoints;

import com.damian.packageservice.response.Response;
import com.damian.packageservice.service.custom.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8080")
public class PackageController {
    @Autowired
    private PackageService packageService;
    @GetMapping(path = "/test")
    public String getGuide() {
        return "This is Packages!";
    }

    @PostMapping(path = "/saveHotelID",produces = MediaType.APPLICATION_JSON_VALUE,params = {"packageID","hotelID"})
    public ResponseEntity<Response> saveHotelID(@RequestParam("packageID") String packageID, @RequestParam("hotelID") String hotelID){
        return packageService.addHotel(packageID,hotelID);
    }
    @PostMapping(path = "/saveVehicleID",produces = MediaType.APPLICATION_JSON_VALUE,params = {"packageID","vehicleID"})
    public ResponseEntity<Response> saveVehicleID(@RequestParam("packageID") String packageID, @RequestParam("vehicleID") String vehicleID){
        return packageService.addVehicle(packageID,vehicleID);
    }
    @DeleteMapping(path = "/deleteHotelID",produces = MediaType.APPLICATION_JSON_VALUE,params = {"packageID","hotelID"})
    public ResponseEntity<Response> deleteHotelID(@RequestParam("packageID") String packageID, @RequestParam("hotelID") String hotelID){
        return packageService.deleteHotel(packageID,hotelID);
    }
    @DeleteMapping(path = "/deleteVehicleID",produces = MediaType.APPLICATION_JSON_VALUE,params = {"packageID","vehicleID"})
    public ResponseEntity<Response> deleteVehicleID(@RequestParam("packageID") String packageID, @RequestParam("vehicleID") String vehicleID){
        return packageService.deleteVehicle(packageID,vehicleID);
    }

}
