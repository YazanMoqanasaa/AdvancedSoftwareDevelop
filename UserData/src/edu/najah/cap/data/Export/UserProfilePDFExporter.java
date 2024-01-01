package edu.najah.cap.data.Export;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserProfilePDFExporter implements PDFExporter {

    @Override
    public void exportToPDF(UserProfile userProfile, List<UserActivity> activities, List<Post> posts, List<Transaction> transactions, String outputPath) {
        Document document = new Document();

        try {
            String fileName = outputPath + File.separator + userProfile.getUserName() + "_profile.pdf";
            File outputFile = new File(fileName);

            if (!outputFile.exists()) {
                PdfWriter.getInstance(document, new FileOutputStream(fileName));
                document.open();

                // Add user profile information to the PDF document
                document.add(new Paragraph("User Profile"));
                document.add(new Paragraph("-----------------------------------------"));
                document.add(new Paragraph("First Name: " + userProfile.getFirstName()));
                document.add(new Paragraph("Last Name: " + userProfile.getLastName()));
                document.add(new Paragraph("Phone Number: " + userProfile.getPhoneNumber()));
                // Add other user details here

                document.close();
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists: " + fileName);
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
