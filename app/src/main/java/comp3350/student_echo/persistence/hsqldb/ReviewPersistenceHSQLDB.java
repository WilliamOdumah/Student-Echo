package comp3350.student_echo.persistence.hsqldb;

import java.util.List;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.persistence.ReviewPersistence;

public class ReviewPersistenceHSQLDB implements ReviewPersistence {

    @Override
    public void addReview(Review r) {

    }

    @Override
    public void deleteReview(Review r) {

    }

    @Override
    public List<CourseReview> getReviewsFor(Course c) {
        return null;
    }

    @Override
    public List<InstructorReview> getReviewsFor(Instructor inst) {
        return null;
    }

    @Override
    public boolean updateReview(Review updatedReview) {
        return false;
    }
}
