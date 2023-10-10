package com.damian.uas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private User userId;
    private String packageDetailsId;
    private String paymentDate;
    private double paymentAmount;
}
