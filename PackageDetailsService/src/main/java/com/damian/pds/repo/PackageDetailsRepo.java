package com.damian.pds.repo;

import com.damian.pds.entity.PackageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;

public interface PackageDetailsRepo extends JpaRepository<PackageDetails,String> {
    List<PackageDetails>findByUserId(String userID);

}
