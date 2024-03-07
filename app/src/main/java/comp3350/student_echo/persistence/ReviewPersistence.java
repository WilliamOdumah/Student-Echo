package comp3350.student_echo.persistence;

import java.util.List;

import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.objects.Review;

public interface ReviewPersistence {

    void addReview(Review r);
    void deleteReview(Review r);
    List<Review> getReviewsFor(Course c);
    List<Review> getReviewsFor(Instructor inst);
    boolean updateReview(Review updatedReview);
    boolean addOrUpdateInteraction(Review r, StudentAccount sa, int newState);
    Integer getInteractionState(Review r, StudentAccount sa);
    default void updateLikeCount(Review r){};
    default void updateDislikeCount(Review r){};
}
