package edu.najah.cap.data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.payment.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
public class ExportUserPayments {

    public void exportUserPaymentsToPDF(String userName, List<Transaction> transactions, String outputPath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath + File.separator + userName + "_payments.pdf"));
            document.open();

            document.add(new Paragraph("User Payments for: " + userName));
            document.add(new Paragraph("-----------------------------------------"));

            for (Transaction transaction : transactions) {
                document.add(new Paragraph("Transaction ID: " + transaction.getId()));
                document.add(new Paragraph("Amount: " + transaction.getAmount()));
                document.add(new Paragraph("Description: " + transaction.getDescription()));
                document.add(new Paragraph("Date: " + Instant.now().toString()));
                document.add(new Paragraph("-----------------------------------------"));
            }

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}