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
@CrossOrigin
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

    @DeleteMapping(path = "/deletePackageDetails", params = {"packageDetailsId","userID"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> delete(@RequestParam("packageDetailsId") String packageDetailsId,@RequestParam("userID")String userID) {
        return packageDetailsService.delete(packageDetailsId,userID);

    }

    @GetMapping(path = "/getAllPackageDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAll() {
        return packageDetailsService.getAll();

    }

    @DeleteMapping(path = "/deletePackageDetailsByUser", params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deletePackageDetailsByUser(@RequestParam("userId") String userId) {
        return packageDetailsService.deletePackageDetailsByUser(userId);

    }
    @DeleteMapping(path = "/deletePD",params = "pID",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePD(@RequestParam("pID")String pID){
        return packageDetailsService.deletePD(pID);
    }
}
