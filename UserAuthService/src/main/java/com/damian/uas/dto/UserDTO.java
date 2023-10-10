package com.damian.uas.dto;


import com.damian.uas.dto.superdto.SuperDTO;
import com.damian.uas.enums.GENDER;
import com.damian.uas.enums.ROLES;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, Serializable {
    @Valid
    private ROLES userRole;
    @NotNull(message = "User Id cannot be null.")
    private String userId;
    @NotBlank(message = "Name cannot be blank.")
    @NotNull(message = "Name cannot be null.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;
    @NotBlank(message = "UserName cannot be blank.")
    @NotNull(message = "UserName cannot be null.")
    @Size(min = 3, max = 10, message = "UserName must be between 3 and 10 characters.")
    private String userName;
    @NotNull(message = "Password cannot be null.")
    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters.")
    private String userPassword;
    @NotNull(message = "NIC cannot be null.")
    @NotBlank(message = "NIC cannot be blank.")
    @Size(min = 10, max = 12, message = "NIC must be between 10 and 12 characters.")
    private String userNIC;
    private String userNICImageLocation;
    @NotNull(message = "Age cannot be null.")
    @Positive(message = "Age must be a positive number.")
    private int userAge;

    @NotNull(message = "Gender cannot be null.")
    private GENDER gender;
    @NotNull(message = "Email cannot be null.")
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email should be valid.")
    private String userEmail;
    @NotNull(message = "Phone cannot be null.")
    @NotBlank(message = "Phone cannot be blank.")
    @Size(min = 10, max = 10, message = "Phone must be 10 characters.")
    private String userPhone;
    @NotNull(message = "Address cannot be null.")
    @NotBlank(message = "Address cannot be blank.")
    private String userAddress;
    @NotNull(message = "Remarks cannot be null.")
    @NotBlank(message = "Remarks cannot be blank.")
    private String remarks;
    private String userImageLocation;
}
