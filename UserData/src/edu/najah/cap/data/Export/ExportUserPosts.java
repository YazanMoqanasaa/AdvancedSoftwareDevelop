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

public class ExportUserPosts implements IPDFExporter {

    private static final Logger logger = Logger.getLogger(Application.class.getName());
    static {
        try {
            // Create a file handler to direct logs to the specified file path
            FileHandler fileHandler = new FileHandler("C:\\Users\\Yazan\\Desktop\\UserData\\Logger\\log_posts.log", true);
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
            String fileName = outputPath + File.separator + userProfile.getUserName() + "_posts.pdf";
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

                document.add(new Paragraph("User Posts for: " + userProfile.getUserName()));
                document.add(new Paragraph("-----------------------------------------"));

                for (Post post : posts) {
                    document.add(new Paragraph("Post Title: " + post.getTitle()));
                    document.add(new Paragraph("Post Body: " + post.getBody()));
                    document.add(new Paragraph("Post Date: " + post.getDate()));
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
