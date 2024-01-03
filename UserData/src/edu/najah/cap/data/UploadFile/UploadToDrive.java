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
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UploadToDrive {
    private static final String APPLICATION_NAME = "Upload File";
    private static final Logger logger = Logger.getLogger(UploadToDrive.class.getName());
    private static UploadToDrive instance;
    private Drive service;

    private UploadToDrive(String serviceAccountJsonPath) {
        try {
            // Configure logging
            configureLogger();

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
            logger.log(Level.SEVERE, "Error initializing Drive service", e);
        }
    }

    private void configureLogger() {
        try {
            // Create file handler for logging
            Handler fileHandler = new FileHandler("application.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error configuring logger", e);
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

            // Log successful file upload
            logger.info("File uploaded successfully. File ID: " + uploadedFile.getId());

            return uploadedFile.getId();
        } catch (IOException e) {
            // Log error during file upload
            logger.log(Level.SEVERE, "Error uploading file", e);
            return null;
        }
    }
}