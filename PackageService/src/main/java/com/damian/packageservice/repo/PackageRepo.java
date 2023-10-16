package com.damian.packageservice.repo;

import com.damian.packageservice.entity.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PackageRepo extends JpaRepository<Packages,String> {


}
