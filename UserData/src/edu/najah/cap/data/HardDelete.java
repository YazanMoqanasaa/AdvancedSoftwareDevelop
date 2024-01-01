package edu.najah.cap.data;

import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class HardDelete {
    private static final Set<String> deletedUsernames = new HashSet<>();

    public static void hardDeleteUser(String userName) {
        UserProfile userProfile = getUserProfile(userName);
        if (userProfile != null) {
            deleteAssociatedFiles(userName);
            deleteUser(userName);
            deletedUsernames.add(userName);
        }
    }

    private static void deleteUser(String userName) {
        UserService.users.remove(userName);
        // Remove the user account entirely
    }

    private static UserProfile getUserProfile(String userName) {
        return UserService.users.get(userName);
    }

    public static boolean isUsernameDeleted(String userName) {
        return deletedUsernames.contains(userName);
    }

    private static void deleteAssociatedFiles(String userName) {
        String outputPath = "C:\\Users\\Yazan\\Desktop\\UserData\\ExportPDF";
        try {
            Files.walk(Path.of(outputPath))
                    .filter(path -> path.getFileName().toString().startsWith(userName))
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                            System.out.println("Deleted file: " + path.getFileName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
