package edu.najah.cap.data.Export;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;

import java.util.List;

public interface IPDFExporter {
    void exportToPDF(UserProfile userProfile, List<UserActivity> activities, List<Post> posts, List<Transaction> transactions, String outputPath);
}