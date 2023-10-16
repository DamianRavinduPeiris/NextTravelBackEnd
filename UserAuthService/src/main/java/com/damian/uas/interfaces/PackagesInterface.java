package com.damian.uas.interfaces;

import com.damian.uas.dto.PackagesDTO;
import com.damian.uas.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("package-service")
public interface PackagesInterface {
    @PostMapping(path = "/savePackage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> save(@RequestBody PackagesDTO packagesDTO);
    @PutMapping(path = "/updatePackage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@RequestBody PackagesDTO packagesDTO);
    @GetMapping(path = "/searchPackage", produces = MediaType.APPLICATION_JSON_VALUE, params = "packageID")
    public ResponseEntity<Response> search(@RequestParam("packageID") String packageID);
    @DeleteMapping(path = "/deletePackage", produces = MediaType.APPLICATION_JSON_VALUE, params = "packageID")
    public ResponseEntity<Response> delete(@RequestParam("packageID") String packageID);
    @GetMapping(path = "/getAllPackages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAllPackages();
}
