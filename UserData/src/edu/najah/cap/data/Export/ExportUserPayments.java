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
import java.time.Instant;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ExportUserPayments implements IPDFExporter {

    private static final Logger logger = Logger.getLogger(Application.class.getName());
    static {
        try {
            // Create a file handler to direct logs to the specified file path
            FileHandler fileHandler = new FileHandler("C:\\Users\\Yazan\\Desktop\\UserData\\Logger\\log_payment.log", true);
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
            String fileName = outputPath + File.separator + userProfile.getUserName() + "_payments.pdf";
            File outputFile = new File(fileName);

            // Setting up file handler for logging
            FileHandler fileHandler = new FileHandler("log_file.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);

            // Check if the file already exists
            if (!outputFile.exists()) {
                PdfWriter.getInstance(document, new FileOutputStream(fileName));
                document.open();

                document.add(new Paragraph("User Payments for: " + userProfile.getUserName()));
                document.add(new Paragraph("-----------------------------------------"));

                for (Transaction transaction : transactions) {
                    document.add(new Paragraph("Transaction ID: " + transaction.getId()));
                    document.add(new Paragraph("Amount: " + transaction.getAmount()));
                    document.add(new Paragraph("Description: " + transaction.getDescription()));
                    document.add(new Paragraph("Date: " + Instant.now().toString()));
                    document.add(new Paragraph("-----------------------------------------"));


                }

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
