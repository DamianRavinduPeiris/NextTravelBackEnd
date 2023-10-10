package com.damian.uas.service.custom;


import com.damian.uas.dto.UserDTO;

import com.damian.uas.response.Response;
import com.damian.uas.service.SuperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends SuperService<UserDTO,String> {
    ResponseEntity<Response> createAndSendResponse(int statusCode, String message, Object data);
    String  handleUploads(MultipartFile imageFile);

}
