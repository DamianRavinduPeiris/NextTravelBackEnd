package com.damian.ps.repo;

import com.damian.ps.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepo extends JpaRepository<Payments,String> {
}
