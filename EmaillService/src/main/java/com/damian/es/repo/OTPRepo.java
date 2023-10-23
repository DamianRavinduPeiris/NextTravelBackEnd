package com.damian.es.repo;

import com.damian.es.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepo extends JpaRepository<OTP,String> {

    Optional<OTP>findByEmail(String email);
}
