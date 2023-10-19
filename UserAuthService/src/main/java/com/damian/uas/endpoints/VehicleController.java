package com.damian.uas.endpoints;

import com.damian.uas.dto.VehicleDTO;
import com.damian.uas.interfaces.VehicleInterface;
import com.damian.uas.response.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/vehicles")
@RestController
@CrossOrigin
public class VehicleController {
    @Autowired
    private VehicleInterface vehicleInterface;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> save(@RequestBody @Valid VehicleDTO vehicleDTO) {
        return vehicleInterface.addVehicle(vehicleDTO);

    }
    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@RequestBody @Valid VehicleDTO vehicleDTO) {
        return vehicleInterface.updateVehicle(vehicleDTO);

    }
    @GetMapping(path = "/search",produces = MediaType.APPLICATION_JSON_VALUE,params = "vehicleID")
    public ResponseEntity<Response>getVehicle(@RequestParam("vehicleID")String vehicleID){
        return vehicleInterface.searchVehicle(vehicleID);

    }

    @DeleteMapping(path = "/delete",produces = MediaType.APPLICATION_JSON_VALUE,params = "vehicleID")
    public ResponseEntity<Response>deleteVehicle(@RequestParam("vehicleID")String vehicleID){
        return vehicleInterface.deleteVehicle(vehicleID);

    }
    @GetMapping(path = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getAll(){
        return vehicleInterface.getAllVehicles();

    }
    @GetMapping(path = "/getVehicleByBrand",params = "vehicleBrand",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getVehicleByBrand(@RequestParam("vehicleBrand")String vehicleBrand){
        return vehicleInterface.getVehicleByBrand(vehicleBrand);

    }

}
