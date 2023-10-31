package com.damian.pds.dto;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component

public class PaymentsDTO implements  Serializable {
    @Valid
    private String paymentId;
    @NotNull(message = "User Id cannot be null.")
    @NotBlank(message = "User Id cannot be blank.")
    private String userId;
    @NotNull(message = "Package Details Id cannot be null.")
    @NotBlank(message = "Package Details Id cannot be blank.")
    private String packageDetailsId;
    @NotNull(message = "Payment Date cannot be null.")
    @NotBlank(message = "Payment Date cannot be blank.")
    private String paymentDate;
    @Positive(message = "Value cannot be negative.")
    private double paymentAmount;
    @NotNull(message = "Payment Image Location cannot be null.")
    @NotBlank(message = "Payment Image Location cannot be blank.")
    private String paymentImageLocation;

}
