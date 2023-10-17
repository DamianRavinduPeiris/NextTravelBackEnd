package com.damian.us.service.custom;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String handleUploads(MultipartFile multipartFile);
}
