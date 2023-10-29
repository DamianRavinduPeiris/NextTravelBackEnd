package com.damian.usr.endpoints;

import com.damian.usr.response.Response;
import com.damian.usr.service.UploadToGDrive;
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
    @PostMapping(path = "/uploadToDrive", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> uploadToDrive(@RequestParam("imageFile") MultipartFile imageFile) {


        return uploadService.uploadToGDrive(uploadService.handleUploads(imageFile));


    }

    @GetMapping(path = "/getImage",params = "imagePath")
    public ResponseEntity<Resource> getImage(@RequestParam("imagePath") String imagePath) {

        return uploadService.getImage(imagePath);
    }



    private java.io.File convertMultipartFileToFile(MultipartFile file)  {
        java.io.File convertedFile = new java.io.File(file.getOriginalFilename());
        try {
            file.transferTo(convertedFile);

            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Could not convert multipartFile to file : "+e.getLocalizedMessage());
        }
        return convertedFile;
    }
}
