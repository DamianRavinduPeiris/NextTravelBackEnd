package com.damian.uas.dto;

import com.damian.uas.dto.superdto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomUpdaterDTO implements SuperDTO, Serializable {
    private String userID;
    private String password;
    private String userImageLocation;
}
