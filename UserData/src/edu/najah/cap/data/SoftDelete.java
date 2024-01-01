package edu.najah.cap.data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.iam.UserType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SoftDelete {
    private static final Set<String> deletedUsernames = new HashSet<>();

    public static void softDeleteUser(String userName) {
        UserProfile userProfile = getUserProfile(userName);
        if (userProfile != null) {
            clearSensitiveInfo(userProfile);
            deletedUsernames.add(userName);
            exportUserProfileToPDF(userProfile);
        }
    }

    /*public static void hardDeleteUser(String userName) {
        UserProfile userProfile = getUserProfile(userName);
        if (userProfile != null) {
            deleteUser(userName);
            deletedUsernames.add(userName);
            exportUserProfileToPDF(userProfile);
        }
    }*/

    private static void clearSensitiveInfo(UserProfile userProfile) {
        userProfile.setFirstName("");
        userProfile.setLastName("");
        userProfile.setPhoneNumber("");
        userProfile.setPassword("");
        userProfile.setRole("");
        userProfile.setDepartment("");
        userProfile.setOrganization("");
        userProfile.setCountry("");
        userProfile.setCity("");
        userProfile.setStreet("");
        userProfile.setPostalCode("");
        userProfile.setBuilding("");
        userProfile.setUserType(UserType.NEW_USER);
    }

    /*private static void deleteUser(String userName) {
        UserService.users.remove(userName);
    }*/

    private static UserProfile getUserProfile(String userName) {
        return UserService.users.get(userName);
    }

    public static boolean isUsernameDeleted(String userName) {
        return deletedUsernames.contains(userName);
    }

    static void exportUserProfileToPDF(UserProfile userProfile) {
        String outputPath = "C:\\Users\\Yazan\\Desktop\\UserData\\Soft&HardDelete";
        String fileName = userProfile.getUserName() + "update_profile.pdf";
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath + File.separator + fileName));
            document.open();
            document.add(new Paragraph("User Profile"));
            document.add(new Paragraph("Username: " + userProfile.getUserName()));
            document.add(new Paragraph("Email: " + userProfile.getEmail()));
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
