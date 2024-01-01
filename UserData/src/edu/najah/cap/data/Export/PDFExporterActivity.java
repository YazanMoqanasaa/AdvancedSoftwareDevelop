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

public class PDFExporterActivity implements IPDFExporter {

    @Override
    public void exportToPDF(UserProfile userProfile, List<UserActivity> activities, List<Post> posts, List<Transaction> transactions, String outputPath) {
        Document document = new Document();

        try {
            String fileName = outputPath + File.separator + userProfile.getUserName() + "_Activity.pdf";
            File outputFile = new File(fileName);

            // Check if the file already exists
            if (!outputFile.exists()) {
                PdfWriter.getInstance(document, new FileOutputStream(fileName));
                document.open();

                // User Profile Information
                document.add(new Paragraph("First Name: " + userProfile.getFirstName()));
                document.add(new Paragraph("Last Name: " + userProfile.getLastName()));
                // Add other user details here

                // User Activity Information
                document.add(new Paragraph("\nUser Activities:"));
                for (UserActivity activity : activities) {
                    document.add(new Paragraph("Activity ID: " + activity.getId()));
                    document.add(new Paragraph("Activity Type: " + activity.getActivityType()));
                    document.add(new Paragraph("Activity Date: " + activity.getActivityDate()));
                    document.add(new Paragraph("-----------------------------------------"));
                }

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