package edu.najah.cap.data.Export;

public class PDFExporterFactory {
    public IPDFExporter createExporter(String type) {
            if ("UserProfile".equalsIgnoreCase(type)) {
                return new UserProfilePDFExporter();
            } else if ("UserActivity".equalsIgnoreCase(type)) {
                return new PDFExporterActivity();
            } else if ("UserPosts".equalsIgnoreCase(type)) {
                return new ExportUserPosts();
            } else if ("UserPayments".equalsIgnoreCase(type)) {
                return new ExportUserPayments();
            }
            // Add other export types if needed
            throw new IllegalArgumentException("Invalid PDF exporter type: " + type);
        }
    }