package com.damian.vs.endpoints;

import com.damian.vs.dto.VehicleDTO;
import com.damian.vs.interfaces.PackageInterface;
import com.damian.vs.response.Response;
import com.damian.vs.service.custom.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private PackageInterface packageInterface;


    @PostMapping(path = "/saveVehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.add(vehicleDTO);


    }

    @PutMapping(path = "/updateVehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.update(vehicleDTO);

    }

    @GetMapping(path = "/searchVehicle", produces = MediaType.APPLICATION_JSON_VALUE, params = "vehicleID")
    public ResponseEntity<Response> searchVehicle(@RequestParam("vehicleID") String vehicleID) {
        return vehicleService.search(vehicleID);

    }

    @DeleteMapping(path = "/deleteVehicle", produces = MediaType.APPLICATION_JSON_VALUE, params = "vehicleID")
    public ResponseEntity<Response> deleteVehicle(@RequestParam("vehicleID") String vehicleID) {
        ResponseEntity<Response> vehicle = searchVehicle(vehicleID);
        VehicleDTO vehicleDTO = (VehicleDTO) vehicle.getBody().getData();
        vehicleService.delete(vehicleID);
        return packageInterface.deleteVehicleID(vehicleDTO.getPackageId(), vehicleDTO.getVehicleId());

    }

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAllVehicles() {
        return vehicleService.getAll();
    }

    @DeleteMapping(path = "/deleteAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAllVehicles(@RequestBody List<String> vehiclesIDList) {
        return vehicleService.deleteAllVehicles(vehiclesIDList);
    }
    @GetMapping(path = "/getVehicleByBrand",params = "vehicleBrand",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getVehicleByBrand(@RequestParam("vehicleBrand")String vehicleBrand){
        return vehicleService.getVehicleByBrand(vehicleBrand);

    }
    @GetMapping(path = "/getVehicleByPackageId",params = "packageId",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getVehicleByPackageId(@RequestParam("packageId")String packageId){
        return vehicleService.getVehiclesByPackageId(packageId);

    }

}
