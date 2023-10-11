package com.damian.uas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany(mappedBy = "packageId",targetEntity = Hotel.class)
    private List<Hotel> hotelList;
    @OneToMany(mappedBy = "packageId",targetEntity = Vehicle.class)
    private List<Vehicle> vehicleList;


}
