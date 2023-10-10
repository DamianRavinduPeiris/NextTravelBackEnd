package com.damian.gs.dto;



import com.damian.gs.enums.GENDER;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class GuideDTO implements SuperDTO, Serializable {
    @Valid
    @NotNull(message = "Guide Id cannot be null.")
    @NotBlank(message = "Guide Id cannot be blank.")
    private String guideId;
    @NotBlank(message = "Name cannot be blank.")
    @NotNull(message = "Name cannot be null.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String guideName;
    @NotBlank(message = "Address cannot be blank.")
    @NotNull(message = "Address cannot be null.")
    @Size(min = 3, max = 50, message = "Address must be between 3 and 50 characters.")
    private String guideAddress;
    @NotNull(message = "Age cannot be null.")
    @Positive(message = "Age must be a positive number.")
    private int guideAge;
    private GENDER guideGender;
    @NotNull(message = "Phone cannot be null.")
    @NotBlank(message = "Phone cannot be blank.")
    @Size(min = 10, max = 10, message = "Phone must be 10 characters.")
    private long guideContact;
    private String guideImageLocation;
    private String guideNICImageLocation;
    private String guideIDImageLocation;
    private String guideExperience;
    @Positive(message = "Value cannot be negative.")
    private double manDayValue;
    private String remarks;
}
