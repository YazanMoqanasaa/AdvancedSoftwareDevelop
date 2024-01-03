package edu.najah.cap.data.Deletion;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SoftDelete implements DeletionStrategy {
    private static final Set<String> deletedUsernames = new HashSet<>();
    private static final Logger logger = Logger.getLogger(SoftDelete.class.getName());
    static {
        try {
            // Create a file handler to direct logs to the specified file path
            FileHandler fileHandler = new FileHandler("C:\\Users\\Yazan\\Desktop\\UserData\\Logger\\soft_delete_log_file.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(String userName, UserProfile userProfile) {
        if (userProfile != null) {
            clearSensitiveInfo(userProfile);
            deletedUsernames.add(userName);
            exportUserProfileToPDF(userProfile);
        }
    }

    private static void clearSensitiveInfo(UserProfile userProfile) {
        userProfile.setFirstName("");
        userProfile.setLastName("");
        userProfile.setPhoneNumber("");
        userProfile.setPassword("");
        userProfile.setRole("");
        userProfile.setDepartment("");
        userProfile.setOrganization("");
        userProfile.setCountry("");
        userProfile.setCity("");
        userProfile.setStreet("");
        userProfile.setPostalCode("");
        userProfile.setBuilding("");
        userProfile.setUserType(UserType.NEW_USER);
    }

    public static boolean isUsernameDeleted(String userName) {
        return deletedUsernames.contains(userName);
    }

    public static void exportUserProfileToPDF(UserProfile userProfile) {
        String outputPath = "C:\\Users\\Yazan\\Desktop\\UserData\\Soft&HardDelete";
        String fileName = userProfile.getUserName() + "update_profile.pdf";
        String filePath = outputPath + File.separator + fileName;

        File existingFile = new File(filePath);
        boolean fileExists = existingFile.exists();

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph("User Profile"));
            document.add(new Paragraph("Username: " + userProfile.getUserName()));
            document.add(new Paragraph("Email: " + userProfile.getEmail()));
            document.close();

            // Check if the file exists and has been updated previously
            if (fileExists) {
                Path path = Paths.get(filePath);
                FileTime lastModifiedTime = Files.getLastModifiedTime(path);
                System.out.println("File exists and was last modified on: " + lastModifiedTime);
                logger.info("File exists and was last modified on : " + path.getFileName());
            } else {
                System.out.println("File does not exist. Created a new file.");
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Exception occurred during PDF export", e);
        }
    }
}