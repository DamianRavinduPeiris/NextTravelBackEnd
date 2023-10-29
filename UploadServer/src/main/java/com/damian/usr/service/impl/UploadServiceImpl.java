package com.damian.usr.service.impl;


import com.damian.usr.custom.AccessTokenDetails;
import com.damian.usr.custom.AccessTokenResponse;
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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
    @Autowired
    AccessTokenDetails accessTokenDetails;
    @Autowired
    private Gson gson;
    private String fileID;
    private String accessToken;
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
                        header("Authorization", "Bearer "+tokenGenerator()).
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
                 googleResponseData = gson.fromJson(response.body(), GoogleResponseData.class);



            } catch (IOException e) {
                throw new RuntimeException("Server threw an IOException : "+e.getLocalizedMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException("Server threw an InterruptedException : "+e.getLocalizedMessage());
            }
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Image Uploaded Successfully");
            fileID = googleResponseData.getId();
            updateAccess();
            response.setData("https://drive.google.com/uc?id="+fileID);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @Override
    public String tokenGenerator() {


            try {
                // URL and form data
                URL url = new URL("https://oauth2.googleapis.com/token");
                String formData = "client_id="+accessTokenDetails.getClientId()+"&client_secret="+accessTokenDetails.getClientSecret()+"&grant_type="+accessTokenDetails.getGrantType()+"&refresh_token="+accessTokenDetails.getRefreshToken();


                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set the request method to POST
                connection.setRequestMethod("POST");

                // Enable input and output streams
                connection.setDoOutput(true);

                // Set content type
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Encode the form data
                byte[] postData = formData.getBytes("UTF-8");

                // Get the output stream and write the form data to it
                OutputStream os = connection.getOutputStream();
                os.write(postData);

                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                AccessTokenResponse accessTokenResponse = gson.fromJson(response.toString(), AccessTokenResponse.class);

                System.out.println("Access Token : "+accessTokenResponse.toString());
                return accessTokenResponse.getAccess_token();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return  null;

        }

    @Override
    public void updateAccess() {
        try {

            String accessToken = tokenGenerator();

            URL url = new URL("https://www.googleapis.com/drive/v3/files/" + fileID + "/permissions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);


            String jsonInputString = "{"
                    + "\"type\": \"anyone\","
                    + "\"role\": \"reader\""
                    + "}";

            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


