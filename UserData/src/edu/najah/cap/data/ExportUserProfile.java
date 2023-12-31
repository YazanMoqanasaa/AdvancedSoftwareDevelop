package edu.najah.cap.data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.iam.UserProfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportUserProfile {


    public void exportUserProfileToPDF(UserProfile userProfile, String outputPath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath + File.separator + userProfile.getUserName() + "_profile.pdf"));
            document.open();

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
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}