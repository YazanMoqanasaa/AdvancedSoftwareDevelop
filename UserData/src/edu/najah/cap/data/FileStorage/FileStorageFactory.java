package edu.najah.cap.data.FileStorage;


public class FileStorageFactory {

    public static FileStorageService getFileStorageService(StorageType type) {
        switch (type) {
            case DROPBOX:
                return new DropboxFileStorage();
            case GOOGLE_DRIVE:
                return new GoogleDriveFileStorage();
            default:
                throw new IllegalArgumentException("Unsupported storage type");
        }
    }
}