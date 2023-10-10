package com.damian.uas.dto;

import com.damian.uas.dto.superdto.SuperDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PackageDetailsDTO implements SuperDTO, Serializable {
    @Pattern(regexp = "^(NEXT)[A-Za-z0-9_]+$", message = "Invalid packageDetails ID.")
    @NotNull(message = "PackageDetails ID cannot be null.")
    @NotBlank(message = "PackageDetails ID cannot be blank.")
    private String packageDetailsId;
    @NotNull(message = "Package ID cannot be null.")
    @NotBlank(message = "Package ID cannot be blank.")
    private String packageId;
    @NotNull(message = "Package Name cannot be null.")
    @NotBlank(message = "Package Name cannot be blank.")
    private String packageCategory;
    @NotNull(message = "Guide ID cannot be null.")
    @NotBlank(message = "Guide ID cannot be blank.")
    private String guideId;
    @NotNull(message = "Vehicle ID cannot be null.")
    @NotBlank(message = "Vehicle ID cannot be blank.")
    private String vehicleId;
    @NotNull(message = "Hotel ID cannot be null.")
    @NotBlank(message = "Hotel ID cannot be blank.")
    private String hotelId;
    @Positive(message = "Value cannot be negative.")
    private int travelDuration;
    @NotNull(message = "Travel Area cannot be null.")
    @NotBlank(message = "Travel Area cannot be blank.")
    private String travelArea;
    @Positive(message = "Value cannot be negative.")
    private int noOfAdults;
    @Positive(message = "Value cannot be negative.")
    private int noOfChildren;
    @Positive(message = "Value cannot be negative.")
    private double totalHeadCount;
    private boolean withPetsOrNot;
    private boolean isGuideIncluded;
    @Positive(message = "Value cannot be negative.")
    private double packageValue;
    @Positive(message = "Value cannot be negative.")
    private double paidValue;
    private String remarks;


}
