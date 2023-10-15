package com.damian.packageservice.service.impl;

import com.damian.packageservice.dto.PackagesDTO;
import com.damian.packageservice.entity.Packages;
import com.damian.packageservice.response.Response;
import com.damian.packageservice.service.custom.PackageService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.damian.packageservice.repo.PackageRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageRepo packageRepo;
    @Autowired
    private Response response;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<Response> add(PackagesDTO packagesDTO) {
        if (search(packagesDTO.getPackageId()).getBody().getData() == null) {
            packageRepo.save(mapper.map(packagesDTO, Packages.class));
            return createAndSendResponse(HttpStatus.CREATED.value(), "Package saved successfully!", null);

        }
        throw new RuntimeException("Package already exists!");
    }

    @Override
    public ResponseEntity<Response> update(PackagesDTO packagesDTO) {
        if (search(packagesDTO.getPackageId()).getBody().getData() != null) {
            packageRepo.save(mapper.map(packagesDTO, Packages.class));
            return createAndSendResponse(HttpStatus.OK.value(), "Package updated successfully!", null);
        }

        throw new RuntimeException("Package does not exist!");
    }

    @Override
    public ResponseEntity<Response> search(String s) {
        Optional<Packages> pack = packageRepo.findById(s);
        if (pack.isPresent()) {
            return createAndSendResponse(HttpStatus.OK.value(), "Package retrieved successfully!", mapper.map(pack.get(), PackagesDTO.class));


        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Package not found!", null);
    }

    @Override
    public ResponseEntity<Response> delete(String s) {
        if (search(s).getBody().getData() != null) {
            packageRepo.deleteById(s);
            return createAndSendResponse(HttpStatus.OK.value(), "Package deleted successfully!", null);

        }
        throw new RuntimeException("Package does not exist!");
    }

    @Override
    public ResponseEntity<Response> getAll() {
        List<Packages> packages = packageRepo.findAll();
        if (packages.isEmpty()) {
            throw new RuntimeException("No packages found!");
        }
        List<PackagesDTO> packagesDTOS = new ArrayList<>();
        packages.forEach((packages1) -> {
            packagesDTOS.add(mapper.map(packages1, PackagesDTO.class));


        });
        return createAndSendResponse(HttpStatus.OK.value(), "Packages retrieved successfully!", packagesDTOS);

    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int statusCode, String msg, Object data) {
        response.setMessage(msg);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.valueOf(statusCode));
    }

    @Override
    public ResponseEntity<Response> addHotel(String packageID, String hotelID) {
        Optional<Packages> pack = packageRepo.findById(packageID);
        if(pack.isPresent()){
            pack.get().getHotelIdList().add(hotelID);
            return createAndSendResponse(HttpStatus.OK.value(),"Hotel added successfully!",null);

        }
        throw new RuntimeException("Package does not exist!");
    }

    @Override
    public ResponseEntity<Response> deleteHotel(String packageID, String hotelID) {
        Optional<Packages> pack = packageRepo.findById(packageID);
        if(pack.isPresent()){
            pack.get().getHotelIdList().remove(hotelID);
            return createAndSendResponse(HttpStatus.OK.value(),"Hotel deleted successfully!",null);

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(),"Package does not exist!",null);
    }

    @Override
    public ResponseEntity<Response> addVehicle(String packageID, String vehicleID) {
        Optional<Packages> pack = packageRepo.findById(packageID);
        if(pack.isPresent()){
            pack.get().getVehicleIdList().add(vehicleID);
            return createAndSendResponse(HttpStatus.OK.value(),"Vehicle added successfully!",null);

        }
        throw new RuntimeException("Package does not exist!");
    }

    @Override
    public ResponseEntity<Response> deleteVehicle(String packageID, String vehicleID) {
        Optional<Packages> pack = packageRepo.findById(packageID);
        if(pack.isPresent()){
            pack.get().getVehicleIdList().remove(vehicleID);
            return createAndSendResponse(HttpStatus.OK.value(),"Vehicle deleted successfully!",null);

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(),"Package does not exist!",null);
    }
}
