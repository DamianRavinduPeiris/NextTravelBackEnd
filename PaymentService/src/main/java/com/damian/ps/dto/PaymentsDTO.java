package com.damian.ps.dto;


import com.damian.ps.dto.superdto.SuperDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentsDTO implements SuperDTO, Serializable {
    @Valid
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentId;
    @NotNull(message = "User Id cannot be null.")
    @NotBlank(message = "User Id cannot be blank.")
    private String userId;
    private String packageDetailsId;
    @NotNull(message = "Payment Date cannot be null.")
    @NotBlank(message = "Payment Date cannot be blank.")
    private String paymentDate;
    @Positive(message = "Value cannot be negative.")
    private double paymentAmount;
    @NotNull(message = "Payment Image cannot be null.")
    @NotBlank(message = "Payment Image cannot be blank.")
    private String paymentImageLocation;

}
