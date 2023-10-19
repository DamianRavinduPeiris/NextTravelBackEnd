package com.damian.packageservice.repo;

import com.damian.packageservice.entity.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface PackageRepo extends JpaRepository<Packages,String> {
    @Query(value = "SELECT package_id FROM packages",nativeQuery = true)
    List<String>getPackageIDs();
    Optional<Packages>findByPackageCategory(String packageCategory);



}
