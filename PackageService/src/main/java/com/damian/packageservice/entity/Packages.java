package com.damian.packageservice.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @ElementCollection
    private List<String> hotelIdList;
    @ElementCollection
    private List<String> vehicleIdList;


}
