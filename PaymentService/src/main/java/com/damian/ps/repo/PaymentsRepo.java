package com.damian.ps.repo;

import com.damian.ps.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentsRepo extends JpaRepository<Payments,String> {

    Payments findByPackageDetailsId(String packageDetailsId);
    List<Payments> findByUserId(String userId);
}
