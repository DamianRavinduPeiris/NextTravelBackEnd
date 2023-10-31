package com.damian.uas.endpoints;

import com.damian.uas.dto.UserDTO;
import com.damian.uas.dto.CustomUpdaterDTO;
import com.damian.uas.interfaces.PackageDetailsInterface;
import com.damian.uas.response.Response;
import com.damian.uas.service.custom.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PackageDetailsInterface packageDetailsInterface;

    @GetMapping(path = "/getUserByUserName", produces = MediaType.APPLICATION_JSON_VALUE, params = {"username", "password"})
    public ResponseEntity<Response> getUserByUserName(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.getUserByUserName(username, password);

    }

    @PostMapping(path = "/saveUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveUser(@RequestBody UserDTO userDTO) {
        return userService.add(userDTO);
    }
    @PutMapping(path = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateUser(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }
    @DeleteMapping(path = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE, params = {"userId"})
    public ResponseEntity<Response> deleteUser(@RequestParam("userId") String userId) {
        packageDetailsInterface.deletePackageDetailsByUser(userId);
        return userService.delete(userId);
    }
    @GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveUser() {
        return userService.getAll();
    }


    @GetMapping(path = "/getByName",produces = MediaType.APPLICATION_JSON_VALUE,params = "name")
    public ResponseEntity<Response> getByName(@RequestParam("name") String name) {
        return userService.findUserByName(name);
    }

    @GetMapping(path ="/getAllNames",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getAllNames(){
        return userService.getAllUsersNames();
    }
    @GetMapping(path ="/getAllIDs",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>getAllIDs(){
        return userService.getAllUsersIDs();
    }


    @PutMapping(path = "/customUpdater",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>customUpdater(@RequestBody CustomUpdaterDTO customUpdaterDTO){
        return userService.customUpdater(customUpdaterDTO);
    }

    @PostMapping(path = "/updatePId",produces = MediaType.APPLICATION_JSON_VALUE,params = {"uId","pId"})
    public ResponseEntity<Response>updatePackageDetailsID(@RequestParam("uId") String uId,@RequestParam("pId") String pId){
        return userService.savePackageDetailsId(uId,pId);

    }

    @DeleteMapping(path ="/deletePID",params = {"uID","pID"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePID(@RequestParam("uID") String uID,@RequestParam("pID") String pID){
        return userService.deletePackageDetailsId(uID,pID);

    }
    @PostMapping(path = "/updatePaymentsID",produces = MediaType.APPLICATION_JSON_VALUE,params = {"uId","pId"})
    public ResponseEntity<Response>updatePaymentsID(@RequestParam("uId") String uId,@RequestParam("pId") String pId){
        return userService.savePaymentsId(uId,pId);

    }

    @DeleteMapping(path ="/deletePaymentsID",params = {"uID","pID"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response>deletePaymentsID(@RequestParam("uID") String uID,@RequestParam("pID") String pID){
        return userService.deletePaymentsId(uID,pID);

    }



}
