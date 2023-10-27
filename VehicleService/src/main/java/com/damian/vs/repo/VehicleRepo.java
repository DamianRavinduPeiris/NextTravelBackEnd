package com.damian.vs.repo;

import com.damian.vs.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleRepo extends JpaRepository<Vehicle,String> {
    Optional<Vehicle>findByVehicleBrand(String vehicleBrand);
    List<Vehicle> findByPackageId(String packageId);
}
