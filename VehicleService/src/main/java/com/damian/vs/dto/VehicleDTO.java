package com.damian.vs.dto;



import com.damian.vs.dto.superdto.SuperDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VehicleDTO implements SuperDTO, Serializable {
    @Valid
    @NotNull(message = "Vehicle ID cannot be null")
    @NotBlank(message = "Vehicle ID cannot be blank")
    private String vehicleId;
    @NotNull(message = "Package ID cannot be null")
    @NotBlank(message = "Package ID cannot be blank")
    private String packageId;
    @NotNull(message = "Vehicle Name cannot be null")
    @NotBlank(message = "Vehicle Name cannot be blank")
    private String vehicleBrand;
    @NotNull(message = "Vehicle Model cannot be null")
    @NotBlank(message = "Vehicle Model cannot be blank")
    private String vehicleCategory;
    @NotBlank(message = "Fuel type cannot be blank")
    @NotNull(message = "Fuel type cannot be null")
    private String fuelType;
    private boolean isHybrid;
    @Positive(message = "Fuel usage cannot be negative")
    private double fuelUsage;
    private String vehicleImageLocation;
    @Positive(message = "Seat capacity cannot be negative")
    private int seatCapacity;
    @NotNull(message = "Vehicle type cannot be null")
    @NotBlank(message = "Vehicle type cannot be blank")
    private String vehicleType;
    @NotNull(message = "Transmission type cannot be null")
    @NotBlank(message = "Transmission type cannot be blank")
    private String transmissionType;
    @NotNull(message = "Driver's name cannot be null")
    @NotBlank(message = "Driver's name cannot be blank")
    private String driversName;
    @Size(min = 10, max = 10, message = "Driver's contact number must be 10 digits")
    @Positive(message = "Driver's contact number cannot be negative")
    private long driversContactNumber;
    private String driversLicenseImageLocation;
    private String remarks;

}
