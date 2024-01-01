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

public class HardDelete implements DeletionStrategy {

    private static final Set<String> deletedUsernames = new HashSet<>();

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
                                System.out.println("Deleted file: " + path.getFileName());
                            } else {
                                System.out.println("File does not exist: " + path.getFileName());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static boolean isUsernameDeleted(String userName) {
        return deletedUsernames.contains(userName);
    }
}

