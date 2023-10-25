package com.damian.vs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String vehicleId;
    private String packageId;
    private String vehicleBrand;
    private String vehicleCategory;
    private String fuelType;
    private boolean isHybrid;
    private double fuelUsage;
    private String vehicleImageLocation;
    private int seatCapacity;
    private String vehicleType;
    private String transmissionType;
    private String driversName;
    private String driversContactNumber;
    private String driversLicenseImageLocation;
    private String remarks;
    private double feeForADay;

}
