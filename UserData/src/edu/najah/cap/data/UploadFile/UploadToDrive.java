package edu.najah.cap.data.UploadFile;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
public class UploadToDrive {
    private static final String APPLICATION_NAME = "Upload File";
    private static UploadToDrive instance;
    private Drive service;
    private UploadToDrive(String serviceAccountJsonPath) {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            // Authenticate using service account credentials
            service = new Drive.Builder(httpTransport, jsonFactory, null)
                    .setHttpRequestInitializer(request -> {
                        // Set your service account credentials here
                    })
                    .setApplicationName(APPLICATION_NAME)
                    .build();

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }
    public static UploadToDrive getInstance(String serviceAccountJsonPath) {
        if (instance == null) {
            instance = new UploadToDrive(serviceAccountJsonPath);
        }
        return instance;
    }
    public String uploadFile(String filePath, String fileName, String parentFolderId) {
        try {
            // Set metadata for the file
            File fileMetadata = new File();
            fileMetadata.setName(fileName);
            fileMetadata.setParents(Collections.singletonList(parentFolderId));

            // Set content of the file
            java.io.File fileContent = new java.io.File(filePath);
            FileContent mediaContent = new FileContent("text/plain", fileContent);

            // Upload the file
            File uploadedFile = service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();

            return uploadedFile.getId();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
