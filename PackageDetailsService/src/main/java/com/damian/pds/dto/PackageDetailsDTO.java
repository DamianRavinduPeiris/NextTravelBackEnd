package com.damian.pds.dto;

import com.damian.pds.dto.superdto.SuperDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PackageDetailsDTO implements SuperDTO, Serializable {
    @Valid
    private String packageDetailsId;
    private String packageId;
    private String packageCategory;
    private String hotelId;
    private String vehicleId;
    private Date startDate;
    private Date endDate;
    private int noOfDays;
    private String travelArea;
    private int noOfAdults;
    private int noOfChildren;
    private int totalHeadCount;
    private  boolean petsStatus;
    private  boolean guideStatus;
    private String guideId;
    private  double totalPackageValue;
    private  String userId;
    private double paidValue;
    private String remarks;


}
