package edu.najah.cap.data;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.exceptions.Util;
import edu.najah.cap.iam.UserProfile;

import static edu.najah.cap.iam.UserService.users;

public class DeleteUser {

    public void softDeleteUser(String userName) throws NotFoundException, SystemBusyException, BadRequestException {
    // Validate the username
    Util.validateUserName(userName);

    // Check if the user exists in the system
    if (!users.containsKey(userName)) {
        throw new NotFoundException("User does not exist");
    }

    // Get the user profile
    UserProfile user = users.get(userName);

    user.setFirstName("");
    user.setLastName("");
    user.setPhoneNumber("");
    user.setPassword("");
    user.setRole("");
    user.setDepartment("");
    user.setOrganization("");
    user.setCountry("");
    user.setCity("");
    user.setStreet("");
    user.setPostalCode("");
    user.setBuilding("");

    System.out.println("User data cleared for: " + userName);
}
}
