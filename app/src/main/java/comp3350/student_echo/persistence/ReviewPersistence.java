package comp3350.student_echo.persistence;

import java.util.List;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;

public interface ReviewPersistence {

    void addReview(Review r);
    void deleteReview(Review r);
    List<Review> getReviewsFor(Course c);
    List<Review> getReviewsFor(Instructor inst);
    boolean updateReview(Review updatedReview);
}
