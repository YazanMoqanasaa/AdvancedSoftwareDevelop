package edu.najah.cap.data.PDFCreateZIP;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PDFZipCreator extends PDFZipTemplate {
    private static PDFZipCreator instance; // Singleton instance
    private static final Logger logger = Logger.getLogger(PDFZipCreator.class.getName());

    // Private constructor to prevent direct instantiation
    private PDFZipCreator() {
        try {
            // Create a file handler to direct logs to the specified file path
            FileHandler fileHandler = new FileHandler("C:\\Users\\Yazan\\Desktop\\UserData\\Logger\\PDFZipCreator.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL); // Set the logging level
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Singleton getInstance method to access the single instance
    public static synchronized PDFZipCreator getInstance() {
        if (instance == null) {
            instance = new PDFZipCreator();
        }
        return instance;
    }

    @Override
    protected String[] getPdfFiles(String userName, String outputPath) {
        return new String[]{
                outputPath + File.separator + userName + "_payments.pdf",
                outputPath + File.separator + userName + "_posts.pdf",
                outputPath + File.separator + userName + "_profile.pdf",
                outputPath + File.separator + userName + "_Activity.pdf"
        };
    }

    @Override
    protected String getZipFileName(String userName, String outputPath) {
        return outputPath + File.separator + userName + "_files.zip";
    }

    @Override
    protected void logZipCreation(String zipFileName) {
        logger.info("ZIP file created: " + zipFileName);
    }
}