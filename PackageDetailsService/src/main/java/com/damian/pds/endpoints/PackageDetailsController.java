package com.damian.pds.endpoints;

import com.damian.pds.dto.PackageDetailsDTO;
import com.damian.pds.response.Response;
import com.damian.pds.service.custom.PackageDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8080")
public class PackageDetailsController {
    @Autowired
    private PackageDetailsService packageDetailsService;

    @PostMapping(path = "/savePackageDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> add(@RequestBody PackageDetailsDTO packageDetailsDTO) {
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
}
