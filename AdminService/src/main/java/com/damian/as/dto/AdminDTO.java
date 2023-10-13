package com.damian.as.dto;

import com.damian.as.dto.superdto.SuperDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdminDTO implements SuperDTO, Serializable {
    @NotNull(message = "Admin ID cannot be null")
    @NotBlank(message = "Admin ID cannot be blank")
    @Size(min = 3, max = 5, message = "Admin ID must be between 3 and 5 characters")
    private String adminID;
    @NotNull(message = "Admin UserName cannot be null")
    @NotBlank(message = "Admin UserName cannot be blank")
    @Size(min = 3, max = 50, message = "Admin UserName must be between 3 and 50 characters")
    private String adminUserName;
    @NotNull(message = "Admin Password cannot be null")
    @NotBlank(message = "Admin Password cannot be blank")
    @Size(min = 3, max = 50, message = "Admin Password must be between 3 and 50 characters")
    private String adminPassword;
}
