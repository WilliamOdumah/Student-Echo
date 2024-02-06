package comp3350.student_echo.persistence;

import java.util.List;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;

public interface ReviewPersistence {
    void insertCourseReview(CourseReview R);
    void insertInstructorReview(InstructorReview R);
    List<CourseReview> getReviewsFor(Course course);
    List<InstructorReview> getReviewsFor(Instructor instructor);
}
