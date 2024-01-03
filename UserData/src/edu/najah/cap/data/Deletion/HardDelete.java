package edu.najah.cap.data.Deletion;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class HardDelete implements DeletionStrategy {

    private static final Set<String> deletedUsernames = new HashSet<>();
    private static final Logger logger = Logger.getLogger("Application");

    static {
        try {
            // Create a file handler to direct logs to the specified file path
            FileHandler fileHandler = new FileHandler("C:\\Users\\Yazan\\Desktop\\UserData\\Logger\\hard_delete_log_file.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void delete(String userName, UserProfile userProfile) throws SystemBusyException, NotFoundException, BadRequestException {
        if (userProfile != null) {
            deleteAssociatedFiles(userName);
            deleteUser(userName);
            deletedUsernames.add(userName);
        }
    }

    private void deleteUser(String userName) throws SystemBusyException, NotFoundException, BadRequestException {
        UserService userService = new UserService();
        userService.deleteUser(userName);
    }

    private void deleteAssociatedFiles(String userName) {
        String outputPath = "C:\\Users\\Yazan\\Desktop\\UserData\\ExportPDF";
        try {
            Files.walk(Path.of(outputPath))
                    .filter(path -> path.getFileName().toString().startsWith(userName))
                    .forEach(path -> {
                        try {
                            if (Files.exists(path)) {
                                Files.delete(path);
                                logger.info("Deleted file: " + path.getFileName());
                            } else {
                                System.out.println("Deleted file: " + path.getFileName());
                                System.out.println("File does not exist: " + path.getFileName());
                                logger.info("File does not exist: " + path.getFileName());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            logger.severe("Error occurred while deleting file: " + path.getFileName() + ". Error: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();

            logger.severe("Error occurred while walking through files: " + e.getMessage());
        }
    }

    public static boolean isUsernameDeleted(String userName) {
        return deletedUsernames.contains(userName);
    }
}