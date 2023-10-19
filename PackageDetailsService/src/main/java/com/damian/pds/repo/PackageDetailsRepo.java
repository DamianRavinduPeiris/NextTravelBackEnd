package com.damian.pds.repo;

import com.damian.pds.entity.PackageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageDetailsRepo extends JpaRepository<PackageDetails,String> {
}
