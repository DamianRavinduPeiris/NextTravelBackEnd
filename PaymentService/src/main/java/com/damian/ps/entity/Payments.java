package com.damian.ps.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payments {
    @Id
    private String paymentId;
    private String userId;
    private String packageDetailsId;
    private String paymentDate;
    private double paymentAmount;
}
