package com.damian.pds.service.custom;

import com.damian.pds.dto.PackageDetailsDTO;
import com.damian.pds.response.Response;
import com.damian.pds.service.SuperService;
import org.springframework.http.ResponseEntity;

public interface PackageDetailsService extends SuperService<PackageDetailsDTO,String> {
    ResponseEntity<Response>getPackageDetailsByUser(String userId);
    ResponseEntity<Response>deletePackageDetailsByUser(String userId);
    String generateId();


}
