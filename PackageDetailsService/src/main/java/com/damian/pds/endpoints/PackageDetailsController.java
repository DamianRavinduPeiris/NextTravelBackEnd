package com.damian.pds.endpoints;

import com.damian.pds.dto.PackageDetailsDTO;
import com.damian.pds.dto.PaymentsDTO;
import com.damian.pds.interfaces.PaymentsInterface;
import com.damian.pds.response.Response;
import com.damian.pds.service.custom.PackageDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class PackageDetailsController {
    @Autowired
    private PackageDetailsService packageDetailsService;
    @Autowired
    private PaymentsInterface paymentsInterface;
    @Autowired
    private PaymentsDTO paymentsDTO;

    @PostMapping(path = "/savePackageDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> add(@RequestBody PackageDetailsDTO packageDetailsDTO) {
        paymentsDTO.setPaymentId("");
        paymentsDTO.setUserId(packageDetailsDTO.getUserId());
        paymentsDTO.setPackageDetailsId(packageDetailsDTO.getPackageDetailsId());
        paymentsDTO.setPaymentDate(packageDetailsDTO.getStartDate().toString());
        paymentsDTO.setPaymentAmount(packageDetailsDTO.getPaidValue());
        paymentsDTO.setPaymentImageLocation("");
        paymentsInterface.savePayment(paymentsDTO);
        return packageDetailsService.add(packageDetailsDTO);

    }

    @PutMapping(path = "/updatePackageDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@RequestBody PackageDetailsDTO packageDetailsDTO) {
        return packageDetailsService.update(packageDetailsDTO);

    }

    @GetMapping(path = "/searchPackageDetails", params = "packageDetailsId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> add(@RequestParam("packageDetailsId") String packageDetailsId) {
        return packageDetailsService.search(packageDetailsId);

    }

    @DeleteMapping(path = "/deletePackageDetails", params = "packageDetailsId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> delete(@RequestParam("packageDetailsId") String packageDetailsId) {
        return packageDetailsService.delete(packageDetailsId);

    }

    @GetMapping(path = "/getAllPackageDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAll() {
        return packageDetailsService.getAll();

    }

    @DeleteMapping(path = "/deletePackageDetailsByUser", params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deletePackageDetailsByUser(@RequestParam("userId") String userId) {
        return packageDetailsService.deletePackageDetailsByUser(userId);

    }
}
