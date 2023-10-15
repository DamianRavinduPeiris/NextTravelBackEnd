package com.damian.vs.service.impl;

import com.damian.vs.dto.VehicleDTO;
import com.damian.vs.entity.Vehicle;
import com.damian.vs.repo.VehicleRepo;
import com.damian.vs.response.Response;
import com.damian.vs.service.custom.VehicleService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private Response response;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<Response> add(VehicleDTO vehicleDTO) {
        if (search(vehicleDTO.getVehicleId()).getBody().getData() == null) {
            vehicleRepo.save(mapper.map(vehicleDTO, Vehicle.class));
            return createAndSendResponse(HttpStatus.CREATED.value(), "Vehicle successfully saved!", null);

        }
        throw new RuntimeException("Vehicle already exists!");


    }

    @Override
    public ResponseEntity<Response> update(VehicleDTO vehicleDTO) {
        if (search(vehicleDTO.getVehicleId()).getBody().getData() == null) {
            throw new RuntimeException("Vehicle does not exist!");

        }
        vehicleRepo.save(mapper.map(vehicleDTO, Vehicle.class));
        return createAndSendResponse(HttpStatus.OK.value(), "Vehicle successfully updated!", null);
    }

    @Override
    public ResponseEntity<Response> search(String s) {
        Optional<Vehicle> vehcicle = vehicleRepo.findById(s);
        if (vehcicle.isPresent()) {
            return createAndSendResponse(HttpStatus.OK.value(), "Vehicle successfully retrieved!", mapper.map(vehcicle.get(), VehicleDTO.class));

        }
        return createAndSendResponse(HttpStatus.NOT_FOUND.value(), "Vehicle not found", null);
    }

    @Override
    public ResponseEntity<Response> delete(String s) {
        if (search(s).getBody().getData() == null) {
            throw new RuntimeException("Vehicle does not exist!");

        }
        vehicleRepo.deleteById(s);
        return createAndSendResponse(HttpStatus.OK.value(), "Vehicle successfully deleted!", null);

    }

    @Override
    public ResponseEntity<Response> getAll() {

        List<Vehicle> vehicles = vehicleRepo.findAll();
        if (vehicles.isEmpty()) {
            throw new RuntimeException("No vehicles found!");

        }
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        vehicles.forEach((vehicle) -> {
            vehicleDTOS.add(mapper.map(vehicle, VehicleDTO.class));

        });
        return createAndSendResponse(HttpStatus.OK.value(), "Vehicles successfully retrieved!", vehicleDTOS);
    }

    @Override
    public ResponseEntity<Response> createAndSendResponse(int statusCode, String msg, Object data) {
        response.setMessage(msg);
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.valueOf(statusCode));
    }
}
