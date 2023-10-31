package com.damian.uas.service.custom;


import com.damian.uas.dto.UserDTO;

import com.damian.uas.dto.CustomUpdaterDTO;
import com.damian.uas.response.Response;
import com.damian.uas.service.SuperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends SuperService<UserDTO,String> {
    ResponseEntity<Response> createAndSendResponse(int statusCode, String message, Object data);
    String  handleUploads(MultipartFile imageFile);
    ResponseEntity<Response>getUserByUserName(String username,String password);
    Boolean passwordValidator(String password,String hashedPassword);
    ResponseEntity<Response>findUserByName(String name);

    boolean validateUserName(String username);
    ResponseEntity<Response>getAllUsersNames();
    ResponseEntity<Response>getAllUsersIDs();
    ResponseEntity<Response>customUpdater(CustomUpdaterDTO customUpdaterDTO);

    ResponseEntity<Response>savePackageDetailsId(String uId,String pId);
    ResponseEntity<Response>deletePackageDetailsId(String uId,String pId);
    ResponseEntity<Response>savePaymentsId(String uId,String pId);
    ResponseEntity<Response>deletePaymentsId(String uId,String pId);


}
