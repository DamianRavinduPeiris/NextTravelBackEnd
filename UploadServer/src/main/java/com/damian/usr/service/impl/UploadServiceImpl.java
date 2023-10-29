package com.damian.usr.service.impl;


import com.damian.usr.response.GoogleResponseData;
import com.damian.usr.response.Response;
import com.damian.usr.service.custom.UploadService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service

public class UploadServiceImpl implements UploadService {
    @Autowired
    private Response response;
    GoogleResponseData googleResponseData;
    @Override
    public String handleUploads(MultipartFile imageFile) {
        // Getting the file name.
        String fileName = imageFile.getOriginalFilename();

        // Specify the destination directory.In this case it is downloads.
        String destinationDirectory = System.getProperty("user.home") + "/Downloads";
        // Create the directory if it doesn't exist.
        File directory = new File(destinationDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create the file path.
        String filePath = destinationDirectory + "/" + fileName;

        // Save the image file.
        try {
            imageFile.transferTo(Paths.get(filePath));
            return filePath.replace("\\", "/");

        } catch (IOException e) {
            throw new RuntimeException("An error occurred while saving the image :" + e.getLocalizedMessage());
        }

    }
    @Override
    public ResponseEntity<Resource> getImage(String imagePath)  {
        File file = new File(imagePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(file.length())
                .body(resource);
    }

    @Override
    public ResponseEntity<Response> uploadToGDrive(String filePath) {


            HttpRequest postRequest = null;

            try {
                Path path = Paths.get(filePath);
                byte[] fileContent = Files.readAllBytes(path);
                postRequest = HttpRequest.newBuilder().
                        uri(new URI("https://www.googleapis.com/upload/drive/v3/files?uploadType=media")).
                        header("content-type", "image/png").
                        header("Authorization", "Bearer "+"ya29.a0AfB_byBRdaN7BJyuPA6Ki_y_fCHsy5CGpcwVVllLfpb2GUwBgqCjqiji77z0R1kG3j5IRult0u2a-UQKuynKw6mAKudR5yL-pd3dIGarQ8W18Y_1OdZRTj5zY0NS0kfbmVAgM4fZ0LtMDFiqUHQu593Br_fuU-VcPuyiaCgYKAT4SARESFQGOcNnCv6WjiKdJvyF3jW21cm-DRg0171").
                        POST(HttpRequest.BodyPublishers.ofByteArray(fileContent)).
                        build();
            } catch (URISyntaxException e) {
                throw new RuntimeException("Server threw an URISyntaxException : "+e.getLocalizedMessage());
            } catch (IOException e) {
                throw new RuntimeException("Server threw an IOException : "+e.getLocalizedMessage());
            }

            HttpClient httpClient = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("response : "+response.body());
                 googleResponseData = new Gson().fromJson(response.body(), GoogleResponseData.class);



            } catch (IOException e) {
                throw new RuntimeException("Server threw an IOException : "+e.getLocalizedMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException("Server threw an InterruptedException : "+e.getLocalizedMessage());
            }
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Image Uploaded Successfully");

            response.setData("https://drive.google.com/uc?id="+googleResponseData.getId());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @Override
    public String tokenGenerator() {
        return null;
    }


}


