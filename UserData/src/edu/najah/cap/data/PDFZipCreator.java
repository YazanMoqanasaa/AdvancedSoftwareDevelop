package edu.najah.cap.data;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PDFZipCreator {

    public void createZipFile(String userName, String outputPath) throws IOException {
        String[] pdfFiles = {
                outputPath + File.separator + userName + "_payments.pdf",
                outputPath + File.separator + userName + "_posts.pdf",
                outputPath + File.separator + userName + "_profile.pdf",
                outputPath + File.separator + userName + "_Activity.pdf"
        };

        String zipFileName = outputPath + File.separator + userName + "_files.zip";

        byte[] buffer = new byte[1024];

        File zipFile = new File(zipFileName);
        if (zipFile.exists()) {
            throw new IOException("ZIP file already exists.");
        }

        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String pdfFile : pdfFiles) {
                File file = new File(pdfFile);
                if (file.exists()) {
                    try (FileInputStream fis = new FileInputStream(pdfFile)) {
                        zos.putNextEntry(new ZipEntry(file.getName()));

                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                    }
                    zos.closeEntry();
                }
            }
        } catch (IOException e) {
            throw new IOException("Error creating ZIP file: " + e.getMessage());
        }
    }
}
