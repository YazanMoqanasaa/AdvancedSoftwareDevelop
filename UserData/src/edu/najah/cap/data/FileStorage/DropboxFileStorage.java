package edu.najah.cap.data.FileStorage;
/*import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

class DropboxFileStorage extends Exception {
    public DropboxFileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class DropboxFileStorage implements FileStorageService {
    private static final Logger logger = Logger.getLogger(DropboxFileStorage.class.getName());
    private static final String ACCESS_TOKEN = "YOUR_DROPBOX_ACCESS_TOKEN";

    private DbxClientV2 client;

    public DropboxFileStorage() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    @Override
    public void uploadFile(String filePath, String destinationPath) throws DropboxFileStorageException {
        try (InputStream in = new FileInputStream(filePath)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + destinationPath)
                    .uploadAndFinish(in);
            logger.log(Level.INFO, "File uploaded to Dropbox: " + metadata.getName());
        } catch (IOException e) {
            handleDropboxException("Failed to read the file", e);
        } catch (UploadErrorException e) {
            handleDropboxException("Failed to upload the file to Dropbox", e);
        }
    }

    private void handleDropboxException(String message, Exception cause) throws DropboxFileStorageException {
        logger.log(Level.SEVERE, message, cause);
        throw new DropboxFileStorageException(message, cause);
    }
}

*/
public class DropboxFileStorage implements FileStorageService{

    @Override
    public void uploadFile(String filePath, String destinationPath) {

    }
}