package com.damian.usr.endpoints;

import com.damian.usr.service.custom.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8080")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping(path = "/upload")
    public String handleUploads(@RequestParam("imageFile") MultipartFile imageFile) {
        return uploadService.handleUploads(imageFile);


    }
}
