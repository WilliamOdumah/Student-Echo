package comp3350.student_echo.business;

import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.persistence.ReviewPersistence;

public class AccessReviews {
    private ReviewPersistence reviewPersistence;

    public AccessReviews() {
        reviewPersistence = Services.getReviewPersistence();
    }

    public List<CourseReview> getReviewFor(Course c) {
        return reviewPersistence.getReviewsFor(c);
    }

    public List<InstructorReview> getReviewFor(Instructor inst) {
        return reviewPersistence.getReviewsFor(inst);
    }
}
