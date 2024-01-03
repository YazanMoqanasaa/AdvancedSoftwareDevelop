package edu.najah.cap.data.Export;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.data.Application;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserProfilePDFExporter implements IPDFExporter {

    private static final Logger logger = Logger.getLogger(Application.class.getName());
    static {
        try {
            // Configure logger to use FileHandler, appending to log_file.txt
            FileHandler fileHandler = new FileHandler("C:\\Users\\Yazan\\Desktop\\UserData\\Logger\\log_profile.txt", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportToPDF(UserProfile userProfile, List<UserActivity> activities, List<Post> posts, List<Transaction> transactions, String outputPath) {
        Document document = new Document();

        try {
            String fileName = outputPath + File.separator + userProfile.getUserName() + "_profile.pdf";
            File outputFile = new File(fileName);

            // Setting up file handler for logging

            if (!outputFile.exists()) {
                PdfWriter.getInstance(document, new FileOutputStream(fileName));
                document.open();

                // Add user profile information to the PDF document
                document.add(new Paragraph("User Profile"));
                document.add(new Paragraph("-----------------------------------------"));
                document.add(new Paragraph("First Name: " + userProfile.getFirstName()));
                document.add(new Paragraph("Last Name: " + userProfile.getLastName()));
                document.add(new Paragraph("Phone Number: " + userProfile.getPhoneNumber()));
                document.add(new Paragraph("Email: " + userProfile.getEmail()));
                document.add(new Paragraph("User Name: " + userProfile.getUserName()));
                document.add(new Paragraph("Password: " + userProfile.getPassword()));
                document.add(new Paragraph("Role: " + userProfile.getRole()));
                document.add(new Paragraph("Department: " + userProfile.getDepartment()));
                document.add(new Paragraph("Organization: " + userProfile.getOrganization()));
                document.add(new Paragraph("City: " + userProfile.getCity()));
                document.add(new Paragraph("Street: " + userProfile.getStreet()));
                document.add(new Paragraph("Postal Code: " + userProfile.getPostalCode()));
                document.add(new Paragraph("Building: " + userProfile.getBuilding()));
                document.add(new Paragraph("User Type: " + userProfile.getUserType()));


                document.close();
                System.out.println("File created: " + fileName);
                logger.log(Level.INFO, "File created: " + fileName);
            } else {
                System.out.println("File already exists: " + fileName);

                logger.log(Level.INFO, "File already exists: " + fileName);
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();

            logger.log(Level.SEVERE, "Exception occurred: ", e);
        }
    }
}
