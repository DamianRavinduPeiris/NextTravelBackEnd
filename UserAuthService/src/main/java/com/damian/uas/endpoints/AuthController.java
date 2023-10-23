package com.damian.uas.endpoints;

import com.damian.uas.dto.UserDTO;
import com.damian.uas.response.Response;
import com.damian.uas.service.custom.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/getAuth", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getAuth(@RequestBody @Valid UserDTO userDTO) {
        System.out.println(userDTO.toString());
        return userService.add(userDTO);
    }

    @PostMapping(path = "/uploadImage",params = "userId")
    public ResponseEntity<Response> uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("userId") String userId) {
        ResponseEntity<Response> user = userService.search(userId);

        UserDTO userData = (UserDTO) user.getBody().getData();
        if(userData!=null){
            userData.setUserImageLocation(userService.handleUploads(imageFile));
            return userService.update(userData);
        }
        throw new RuntimeException("User not found!");




    }

    @GetMapping(path = "/hello")
    public String  getRole(){
        return "Hello nigger!";

    }





}
