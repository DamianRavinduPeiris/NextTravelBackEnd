package com.damian.packageservice.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Packages {
    @Id
    @NonNull
    private String packageId;
    @NonNull
    private String packageCategory;
    @NonNull
    private String hotelCategory;
    @NonNull
    private String vehicleCategory;
    @ElementCollection
    private List<String> hotelIdList;
    @ElementCollection
    private List<String> vehicleIdList;


}
