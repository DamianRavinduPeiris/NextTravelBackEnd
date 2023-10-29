package com.damian.usr.service.custom;

import com.damian.usr.response.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String handleUploads(MultipartFile multipartFile);
    ResponseEntity<Resource> getImage(String imagePath);
    ResponseEntity<Response> uploadToGDrive(String imagePath);
    String tokenGenerator();
}
