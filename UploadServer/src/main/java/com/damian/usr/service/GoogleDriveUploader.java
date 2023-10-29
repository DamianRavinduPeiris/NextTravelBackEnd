package com.damian.usr.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;

public class GoogleDriveUploader {
    public static String uploadFile(java.io.File file) throws Exception {
        Drive service = getDriveService(); // Initialize Drive service
        File driveFile = new File();
        driveFile.setName(file.getName());

        FileContent mediaContent = new FileContent("image/jpeg", file);

        File uploadedFile = service.files().create(driveFile, mediaContent)
                .setFields("id")
                .execute();

        return "https://drive.google.com/file/d/" + uploadedFile.getId() + "/view";
    }

    private static Drive getDriveService() throws Exception {
        // Load client secrets JSON file (downloaded from Google Developers Console)

        java.io.File file = new ClassPathResource("credentials.json").getFile();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new FileReader(file));

        // Initialize the Drive service
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JacksonFactory.getDefaultInstance())
                .setClientSecrets(clientSecrets)
                .build();

        return new Drive.Builder(httpTransport, JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("NextTravel - Image Handler")
                .build();
    }
}
