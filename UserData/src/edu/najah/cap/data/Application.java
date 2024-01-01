package edu.najah.cap.data;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.exceptions.Util;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.PaymentService;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;
import edu.najah.cap.posts.PostService;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final IUserActivityService userActivityService = new UserActivityService();
    private static final IPayment paymentService = new PaymentService();
    private static final IUserService userService = new UserService();
    private static final IPostService postService = new PostService();

    private static String loginUserName;

    public static void main(String[] args) throws SystemBusyException, NotFoundException, BadRequestException {
        generateRandomData();
        Instant start = Instant.now();
        System.out.println("Application Started: " + start);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        System.out.println("Note: You can use any of the following usernames: user0, user1, user2, user3, .... user99");
        String userName = scanner.nextLine();
        setLoginUserName(userName);
        //TODO Your application starts here. Do not Change the existing code
        String outputPath = "C:\\Users\\Yazan\\Desktop\\UserData\\ExportPDF";
        //****************************



        System.out.println("Choose an action:");
        System.out.println("1. Soft Delete User");
        System.out.println("2. Hard Delete User");
        System.out.println("3. Export User Data");
        System.out.println("4. Convert a zip file");
        System.out.println("5. Exit");

        int choice = scanner.nextInt();
        UserProfile userProfile = userService.getUser(userName);

        switch (choice) {
            case 1:
                SoftDelete.softDeleteUser(userName);

                // Check if the username has been deleted
                if (SoftDelete.isUsernameDeleted(userName)) {
                    System.out.println("User data has been soft-deleted.");

                    // Export user profile before deletion
                    SoftDelete.exportUserProfileToPDF(userProfile);
                } else {
                    System.out.println("Soft delete failed for user: " + userName);
                }
                break;
            case 2:
                HardDelete.hardDeleteUser(userName);

                // Check if the username has been deleted
                if (HardDelete.isUsernameDeleted(userName)) {
                    System.out.println("User data has been hard-deleted.");
                } else
                    System.out.printf("ERROR!!!!");
                break;
            case 3:
                List<UserActivity> userActivities = userActivityService.getUserActivity(userName);
                List<Post> userPosts = postService.getPosts(userName);
                List<Transaction> userTransactions = paymentService.getTransactions(userName);

                ExportUserProfile pdfExporterUserProfile = new ExportUserProfile();
                pdfExporterUserProfile.exportUserProfileToPDF(userProfile, outputPath);

                PDFExporterActivity pdfExporterActivity = new PDFExporterActivity();
                pdfExporterActivity.exportUserProfileAndActivitiesToPDF(userProfile, userActivities, outputPath);

                ExportUserPosts exportUserPosts = new ExportUserPosts();
                exportUserPosts.exportUserPostsToPDF(userName, userPosts, outputPath);

                ExportUserPayments exportUserPayments = new ExportUserPayments();
                exportUserPayments.exportUserPaymentsToPDF(userName, userTransactions, outputPath);

                break;
            case 4:
                PDFZipCreator zipCreator = new PDFZipCreator();
                zipCreator.createZipFile(userName, outputPath);
                break;
            case 5:
                // Exit the application
                System.out.println("Exiting the application.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }



        //TODO Your application ends here. Do not Change the existing code
    Instant end = Instant.now();
        System.out.println("Application Ended: "+end);
}



    private static void generateRandomData() {
        Util.setSkipValidation(true);
        for (int i = 0; i < 100; i++) {
            generateUser(i);
            generatePost(i);
            generatePayment(i);
            generateActivity(i);
        }
        System.out.println("Data Generation Completed");
        Util.setSkipValidation(false);
    }


    private static void generateActivity(int i) {
        for (int j = 0; j < 100; j++) {
            try {
                if(UserType.NEW_USER.equals(userService.getUser("user" + i).getUserType())) {
                    continue;
                }
            } catch (Exception e) {
                System.err.println("Error while generating activity for user" + i);
            }
            userActivityService.addUserActivity(new UserActivity("user" + i, "activity" + i + "." + j, Instant.now().toString()));
        }
    }

    private static void generatePayment(int i) {
        for (int j = 0; j < 100; j++) {
            try {
                if (userService.getUser("user" + i).getUserType() == UserType.PREMIUM_USER) {
                    paymentService.pay(new Transaction("user" + i, i * j, "description" + i + "." + j));
                }
            } catch (Exception e) {
                System.err.println("Error while generating post for user" + i);
            }
        }
    }

    private static void generatePost(int i) {
        for (int j = 0; j < 100; j++) {
            postService.addPost(new Post("title" + i + "." + j, "body" + i + "." + j, "user" + i, Instant.now().toString()));
        }
    }

    private static void generateUser(int i) {
        UserProfile user = new UserProfile();
        user.setUserName("user" + i);
        user.setFirstName("first" + i);
        user.setLastName("last" + i);
        user.setPhoneNumber("phone" + i);
        user.setEmail("email" + i);
        user.setPassword("pass" + i);
        user.setRole("role" + i);
        user.setDepartment("department" + i);
        user.setOrganization("organization" + i);
        user.setCountry("country" + i);
        user.setCity("city" + i);
        user.setStreet("street" + i);
        user.setPostalCode("postal" + i);
        user.setBuilding("building" + i);
        user.setUserType(getRandomUserType(i));
        userService.addUser(user);
    }

    private static UserType getRandomUserType(int i) {
        if (i > 0 && i < 3) {
            return UserType.NEW_USER;
        } else if (i > 3 && i < 7) {
            return UserType.REGULAR_USER;
        } else {
            return UserType.PREMIUM_USER;
        }
    }

    public static String getLoginUserName() {
        return loginUserName;
    }

    private static void setLoginUserName(String loginUserName) {
        Application.loginUserName = loginUserName;
    }
}
