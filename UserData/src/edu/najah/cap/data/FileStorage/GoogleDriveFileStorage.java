package edu.najah.cap.data.FileStorage;

/*import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;


class GoogleDriveFileStorageException extends Exception {
    public GoogleDriveFileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class GoogleDriveFileStorage implements FileStorageService {
    private static final Logger logger = Logger.getLogger(GoogleDriveFileStorage.class.getName());

    public GoogleDriveFileStorage() throws GoogleDriveFileStorageException {
        try {
            driveService = getDriveService();
        } catch (IOException | GeneralSecurityException e) {
            handleDriveServiceException("Failed to initialize Google Drive service", e);
        }
    }

    @Override
    public void uploadFile(String filePath, String destinationPath) throws GoogleDriveFileStorageException {
        try {
            File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            logger.log(Level.INFO, "File uploaded: " + uploadedFile.getId());
        } catch (IOException e) {
            handleDriveServiceException("Failed to upload file", e);
        }
    }

    private static Drive getDriveService() throws IOException, GeneralSecurityException {
        try {
            return new Drive.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (IOException | GeneralSecurityException e) {
            handleDriveServiceException("Failed to create Drive service", e);
            throw e;
        }
    }

    private static void handleDriveServiceException(String message, Exception cause) throws GoogleDriveFileStorageException {
        logger.log(Level.SEVERE, message, cause);
        throw new GoogleDriveFileStorageException(message, cause);
    }
}


*/

public class GoogleDriveFileStorage implements FileStorageService{
    @Override
    public void uploadFile(String filePath, String destinationPath) {

    }
}