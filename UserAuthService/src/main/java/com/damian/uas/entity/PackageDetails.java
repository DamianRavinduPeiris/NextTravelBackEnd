package com.damian.uas.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PackageDetails {
    @Id
    private String packageDetailsId;
    private String packageId;
    private String packageCategory;
    private String guideId;
    private String vehicleId;
    private String hotelId;
    private int travelDuration;
    private String travelArea;
    private int noOfAdults;
    private int noOfChildren;
    private double totalHeadCount;
    private boolean withPetsOrNot;
    private boolean isGuideIncluded;
    private double packageValue;
    private double paidValue;
    private String remarks;
    @ManyToOne
    private User userId;

}
