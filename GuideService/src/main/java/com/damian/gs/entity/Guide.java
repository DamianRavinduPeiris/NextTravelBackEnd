package com.damian.gs.entity;


import com.damian.gs.entity.superentity.SuperEntity;
import com.damian.gs.enums.GENDER;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "guide")
public class Guide implements SuperEntity {
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
