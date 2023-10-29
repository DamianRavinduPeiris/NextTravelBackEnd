package com.damian.usr.endpoints;

import com.damian.usr.service.GoogleDriveUploader;
import com.damian.usr.service.custom.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("")
@CrossOrigin
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleUploads(@RequestParam("imageFile") MultipartFile imageFile) {

        return uploadService.handleUploads(imageFile);


    }

    @GetMapping(path = "/getImage",params = "imagePath")
    public ResponseEntity<Resource> getImage(@RequestParam("imagePath") String imagePath) {

        return uploadService.getImage(imagePath);
    }

    @PostMapping(path = "/uploadToDrive", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String driveUploader(@RequestParam("imageFile") MultipartFile imageFile) {
        try {
            java.io.File file = convertMultipartFileToFile(imageFile);
            return GoogleDriveUploader.uploadFile(file);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    private java.io.File convertMultipartFileToFile(MultipartFile file)  {
        java.io.File convertedFile = new java.io.File(file.getOriginalFilename());
        try {
            file.transferTo(convertedFile);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return convertedFile;
    }
}
