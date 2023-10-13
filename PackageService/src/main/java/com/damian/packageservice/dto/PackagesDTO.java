package com.damian.packageservice.dto;



import com.damian.packageservice.dto.superdto.SuperDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PackagesDTO implements SuperDTO, Serializable {
    @Valid
    @NotNull(message = "Package Id cannot be null.")
    @NotBlank(message = "Package Id cannot be blank.")
    private String packageId;
    @NotNull(message = "Package Name cannot be null.")
    @NotBlank(message = "Package Name cannot be blank.")
    private String packageCategory;
    @NotNull(message = "Hotel Name cannot be null.")
    @NotBlank(message = "Hotel Name cannot be blank.")
    private String hotelCategory;
    @NotNull(message = "Vehicle Name cannot be null.")
    @NotBlank(message = "Vehicle Name cannot be blank.")
    private String vehicleCategory;


}
