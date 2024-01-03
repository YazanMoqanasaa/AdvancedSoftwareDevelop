package edu.najah.cap.data.PDFCreateZIP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

abstract class PDFZipTemplate {
    private static final Logger logger = Logger.getLogger(PDFZipTemplate.class.getName());

    protected abstract String[] getPdfFiles(String userName, String outputPath);

    protected abstract String getZipFileName(String userName, String outputPath);

    protected abstract void logZipCreation(String zipFileName);

    // Template method that orchestrates the process
    public final void createZipFile(String userName, String outputPath) throws IOException {
        String[] pdfFiles = getPdfFiles(userName, outputPath);
        String zipFileName = getZipFileName(userName, outputPath);
        byte[] buffer = new byte[1024];

        File zipFile = new File(zipFileName);
        if (zipFile.exists()) {
            logger.warning("ZIP file already exists: " + zipFileName);
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

            logZipCreation(zipFileName);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating ZIP file: " + e.getMessage(), e);
            throw new IOException("Error creating ZIP file: " + e.getMessage());
        }
    }
}