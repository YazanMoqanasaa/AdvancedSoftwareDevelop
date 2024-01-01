package edu.najah.cap.data.Deletion;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.UserProfile;

public interface DeletionStrategy {
    void delete(String userName, UserProfile userProfile) throws SystemBusyException, NotFoundException, BadRequestException;

}
