package com.damian.usr.service;

import com.damian.usr.response.GoogleResponseData;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadToGDrive {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Damian Peiris\\Downloads\\pinkpanther.png";

        HttpRequest postRequest = null;
        try {
            // Read the file content as bytes
            Path path = Paths.get(filePath);
            byte[] fileContent = Files.readAllBytes(path);
            postRequest = HttpRequest.newBuilder().
                    uri(new URI("https://www.googleapis.com/upload/drive/v3/files?uploadType=media")).
                    header("content-type", "image/png").
                    header("Authorization", "Bearer "+"ya29.a0AfB_byDqYgjB8hXUoxIJHiLtoEPgEPATSfbqP4ZjDh4oCh0Y-AeUpc1wEmq8Qj56AdEcV3lLlfjlRuNB0ol8vEFCF16Cdq8EWrFucLFTuRjzhG0z2cqpdTWfR36nNRANQnELQaIjXCmDzB4GF4CYTtpII1c-MUOcOHm8aCgYKAYsSARESFQGOcNnCEsebN5lVXMXFqKTySmSDlg0171").
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
            GoogleResponseData googleResponseData = new Gson().fromJson(response.body(), GoogleResponseData.class);
            System.out.println("Link : "+"https://drive.google.com/uc?id="+googleResponseData.getId());


        } catch (IOException e) {
            throw new RuntimeException("Server threw an IOException : "+e.getLocalizedMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("Server threw an InterruptedException : "+e.getLocalizedMessage());
        }
    }

}
