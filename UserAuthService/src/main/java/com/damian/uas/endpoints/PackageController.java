package com.damian.uas.endpoints;

import com.damian.uas.dto.PackagesDTO;
import com.damian.uas.interfaces.PackagesInterface;
import com.damian.uas.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/packages")
@RestController
@CrossOrigin
public class PackageController {
    @Autowired
    private PackagesInterface packagesInterface;
    @PostMapping(path = "/save",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>add(@RequestBody PackagesDTO packagesDTO){
        return packagesInterface.save(packagesDTO);

    }
    @PutMapping(path = "/update",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>update(@RequestBody PackagesDTO packagesDTO){
        return packagesInterface.update(packagesDTO);

    }
    @GetMapping(path = "/search",produces = MediaType.APPLICATION_JSON_VALUE,params = "packageID")
    public ResponseEntity<Response>search(@RequestParam("packageID")String packageID){
        return packagesInterface.search(packageID);

    }
    @DeleteMapping(path = "/delete",produces = MediaType.APPLICATION_JSON_VALUE,params = "packageID")
    public ResponseEntity<Response>delete(@RequestParam("packageID")String packageID){
        return packagesInterface.delete(packageID);

    }
    @GetMapping(path = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getAll(){
        return packagesInterface.getAllPackages();

    }
}
