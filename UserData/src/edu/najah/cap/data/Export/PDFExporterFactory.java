package edu.najah.cap.data.Export;

import edu.najah.cap.data.Application;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PDFExporterFactory {
    private static final Logger logger = Logger.getLogger(Application.class.getName());
    static {
        try {
            // Create a file handler to direct logs to a specific file path
            FileHandler fileHandler = new FileHandler("C:\\Users\\Yazan\\Desktop\\UserData\\Logger\\factory_log_file.txt", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public IPDFExporter createExporter(String type) {
        try {
            if ("UserProfile".equalsIgnoreCase(type)) {
                logger.log(Level.INFO, "Creating UserProfilePDFExporter");
                return new UserProfilePDFExporter();
            } else if ("UserActivity".equalsIgnoreCase(type)) {
                logger.log(Level.INFO, "Creating PDFExporterActivity");
                return new PDFExporterActivity();
            } else if ("UserPosts".equalsIgnoreCase(type)) {
                logger.log(Level.INFO, "Creating ExportUserPosts");
                return new ExportUserPosts();
            } else if ("UserPayments".equalsIgnoreCase(type)) {
                logger.log(Level.INFO, "Creating ExportUserPayments");
                return new ExportUserPayments();
            }
            // Add other export types if needed
            throw new IllegalArgumentException("Invalid PDF exporter type: " + type);
        } catch (Exception e) {

            logger.log(Level.SEVERE, "Exception occurred while creating PDF exporter", e);
            throw e;
        }
    }
}
