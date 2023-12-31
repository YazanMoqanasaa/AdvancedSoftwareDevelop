package edu.najah.cap.data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.posts.Post;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportUserPosts {
    public void exportUserPostsToPDF(String userName, List<Post> posts, String outputPath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath + File.separator + userName + "_posts.pdf"));
            document.open();

            document.add(new Paragraph("User Posts for: " + userName));
            document.add(new Paragraph("-----------------------------------------"));

            for (Post post : posts) {
                document.add(new Paragraph("Post Title: " + post.getTitle()));
                document.add(new Paragraph("Post Body: " + post.getBody()));
                document.add(new Paragraph("Post Date: " + post.getDate()));
                document.add(new Paragraph("-----------------------------------------"));
            }

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}