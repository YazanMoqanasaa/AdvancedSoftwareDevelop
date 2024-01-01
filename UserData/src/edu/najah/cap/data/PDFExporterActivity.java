package edu.najah.cap.data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.iam.UserProfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFExporterActivity {

    public void exportUserProfileAndActivitiesToPDF(UserProfile userProfile, List<UserActivity> activities, String outputPath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath + File.separator + userProfile.getUserName() + "_Activity.pdf"));
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
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}