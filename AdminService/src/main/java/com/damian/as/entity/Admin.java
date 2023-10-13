package com.damian.as.entity;

import com.damian.as.entity.superentity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "adminTable")
public class Admin implements SuperEntity {
    @Id
    private String adminId;
    private String adminUserName;
    private String adminPassword;

}
