package comp3350.student_echo.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;

public interface ReviewPersistence {
    void addCourseReview(CourseReview review);
    List<CourseReview> getAllCourseReviews();
    void addInstructorReview(InstructorReview review);
    List<InstructorReview> getAllInstructorReviews();
    void deleteCourseReview(String reviewId);
    void deleteInstructorReview(String reviewId);
    void editReview(CourseReview updatedReview);
    CourseReview getCourseReviewById(String reviewId);
    InstructorReview getInstructorReviewById(String reviewId);
    boolean updateCourseReviewInDatabase(CourseReview updatedReview);
    boolean updateInstructorReviewInDatabase(InstructorReview updatedReview);
    List<CourseReview> getReviewsFor(Course c);
    List<InstructorReview> getReviewsFor(Instructor inst);

}
