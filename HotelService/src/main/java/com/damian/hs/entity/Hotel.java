package com.damian.hs.entity;

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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String hotelId;
    private String packageId;
    private String hotelName;
    private String hotelCategory;
    private String hotelLocation;
    private String hotelLocationWithCoordinates;
    private String hotelContactEmail;
    private String hotelContact1;
    private String hotelContact2;
    private boolean isPetsAllowed;
    private double hotelFee;
    private String cancellationCriteria;
    private String remarks;
}