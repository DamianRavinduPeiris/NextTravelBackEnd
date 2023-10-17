package com.damian.uas.endpoints;

import com.damian.uas.interfaces.UploadInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/uploads")
@CrossOrigin
public class UploadController {
    @Autowired
    private UploadInterface uploadInterface;

    @PostMapping(path = "/handleUploads",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleUploads(@RequestParam("imageFile") MultipartFile imageFile) {
        return uploadInterface.handleUploads(imageFile);
    }


}
