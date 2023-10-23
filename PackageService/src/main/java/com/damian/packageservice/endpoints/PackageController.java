package com.damian.packageservice.endpoints;

import com.damian.packageservice.dto.PackagesDTO;
import com.damian.packageservice.interfaces.HotelInterface;
import com.damian.packageservice.interfaces.VehicleInterface;
import com.damian.packageservice.response.Response;
import com.damian.packageservice.service.custom.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin
public class PackageController {
    @Autowired
    private PackageService packageService;
    @Autowired
    private HotelInterface hotelInterface;
    @Autowired
    private VehicleInterface vehicleInterface;

    @GetMapping(path = "/test")
    public String getGuide() {
        return "This is Packages!";

    }

    @PostMapping(path = "/saveHotelID", produces = MediaType.APPLICATION_JSON_VALUE, params = {"packageID", "hotelID"})
    public ResponseEntity<Response> saveHotelID(@RequestParam("packageID") String packageID, @RequestParam("hotelID") String hotelID) {
        return packageService.addHotel(packageID, hotelID);
    }

    @PostMapping(path = "/saveVehicleID", produces = MediaType.APPLICATION_JSON_VALUE, params = {"packageID", "vehicleID"})
    public ResponseEntity<Response> saveVehicleID(@RequestParam("packageID") String packageID, @RequestParam("vehicleID") String vehicleID) {
        return packageService.addVehicle(packageID, vehicleID);
    }

    @DeleteMapping(path = "/deleteHotelID", produces = MediaType.APPLICATION_JSON_VALUE, params = {"packageID", "hotelID"})
    public ResponseEntity<Response> deleteHotelID(@RequestParam("packageID") String packageID, @RequestParam("hotelID") String hotelID) {
        return packageService.deleteHotel(packageID, hotelID);
    }

    @DeleteMapping(path = "/deleteVehicleID", produces = MediaType.APPLICATION_JSON_VALUE, params = {"packageID", "vehicleID"})
    public ResponseEntity<Response> deleteVehicleID(@RequestParam("packageID") String packageID, @RequestParam("vehicleID") String vehicleID) {
        return packageService.deleteVehicle(packageID, vehicleID);
    }

    @PostMapping(path = "/savePackage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> save(@RequestBody PackagesDTO packagesDTO) {
        return packageService.add(packagesDTO);

    }

    @PutMapping(path = "/updatePackage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@RequestBody PackagesDTO packagesDTO) {
        return packageService.update(packagesDTO);

    }

    @GetMapping(path = "/searchPackage", produces = MediaType.APPLICATION_JSON_VALUE, params = "packageID")
    public ResponseEntity<Response> search(@RequestParam("packageID") String packageID) {
        return packageService.search(packageID);

    }

    @DeleteMapping(path = "/deletePackage", produces = MediaType.APPLICATION_JSON_VALUE, params = "packageID")
    public ResponseEntity<Response> delete(@RequestParam("packageID") String packageID) {
        List<String> hotelsList = packageService.getHotelsList(packageID);
        List<String> vehiclesList = packageService.getVehiclesList(packageID);
        packageService.delete(packageID);
        System.out.println("Hotels List : " + hotelsList);
        System.out.println("Vehicles List : " + vehiclesList);

        hotelInterface.deleteAllHotels(hotelsList);
        return vehicleInterface.deleteAllVehicles(vehiclesList);

    }

    @GetMapping(path = "/getAllPackages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAllPackages() {
        return packageService.getAll();
    }

    @GetMapping(path = "/getAllPackageIDs")
    public List<String> getAllPackageIDs() {
        return packageService.getPackageIDs();
    }


    @PutMapping(path = "/updateHotelPackageId", produces = MediaType.APPLICATION_JSON_VALUE, params = {"oldPackageId", "newPackageId", "hotelId"})
    public ResponseEntity<Response> updateHotelPackageId(@RequestParam("oldPackageId") String oldPackageId, @RequestParam("newPackageId") String newPackageId, @RequestParam("hotelId") String hotelId) {
        return packageService.updateHotelPackageID(oldPackageId, newPackageId, hotelId);
    }

    @PutMapping(path = "/updateVehiclePackageId", produces = MediaType.APPLICATION_JSON_VALUE, params = {"oldPackageId", "newPackageId", "vehicleId"})
    public ResponseEntity<Response> updateVehiclePackageId(@RequestParam("oldPackageId") String oldPackageId, @RequestParam("newPackageId") String newPackageId, @RequestParam("vehicleId") String vehicleId) {
        return packageService.updateVehiclePackageID(oldPackageId, newPackageId, vehicleId);
    }

    @GetMapping(path = "/getPackageByCategory", produces = MediaType.APPLICATION_JSON_VALUE, params = "category")
    public ResponseEntity<Response> getPackageByCategory(@RequestParam("category") String category) {
        return packageService.getPackageByCategory(category);
    }

    @GetMapping(path = "/getPackages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getPackages() {
        return packageService.getPackageCategoryList();
    }

}
