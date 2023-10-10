package com.damian.packageservice.entity;

import com.damian.packageservice.entity.superentity.SuperEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Packages implements SuperEntity {
    @Id
    private String packageId;
    private String packageCategory;
    private String hotelCategory;
    private String vehicleCategory;

}
