package com.damian.uas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Packages {
    @Id
    private String packageId;
    private String packageCategory;
    private String hotelCategory;
    private String vehicleCategory;

}
