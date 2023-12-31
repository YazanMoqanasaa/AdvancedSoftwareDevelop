package edu.najah.cap.data;


import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;

public class SoftDelete {

    private final IUserService userService;
    private final IUserActivityService userActivityService;
    private final IPayment paymentService;
    private final IPostService postService;

    public SoftDelete(IUserService userService, IUserActivityService userActivityService,
                            IPayment paymentService, IPostService postService) {
        this.userService = userService;
        this.userActivityService = userActivityService;
        this.paymentService = paymentService;
        this.postService = postService;
    }

    public void softDeleteUserData(String userName) {
        try {
            // Get user profile
            UserProfile userProfile = userService.getUser(userName);

            // Remove sensitive user details for soft delete
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

            // Optionally set user type to a default value if needed
            // userProfile.setUserType(UserType.DEFAULT_USER);

            // Update user profile after soft delete
            userService.updateUser(userProfile);

            // Inform about successful soft deletion
            System.out.println("Soft deletion successful for: " + userName);
        } catch (NotFoundException | SystemBusyException | BadRequestException e) {
            System.err.println("Soft deletion failed for user: " + userName + ". Reason: " + e.getMessage());
        }
    }
}
