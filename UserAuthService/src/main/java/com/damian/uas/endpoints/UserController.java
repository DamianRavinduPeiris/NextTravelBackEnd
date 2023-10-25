package com.damian.uas.endpoints;

import com.damian.uas.dto.UserDTO;
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



}
