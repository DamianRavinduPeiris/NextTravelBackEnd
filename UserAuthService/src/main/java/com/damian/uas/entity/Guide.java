package com.damian.uas.entity;

import com.damian.uas.enums.GENDER;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Guide {
    @Id
    private String guideId;
    private String guideName;
    private String guideAddress;
    private int guideAge;
    private GENDER guideGender;
    private long guideContact;
    private String guideImageLocation;
    private String guideNICImageLocation;
    private String guideIDImageLocation;
    private String guideExperience;
    private double manDayValue;
    private String remarks;
}
