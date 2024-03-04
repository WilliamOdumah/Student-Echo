package comp3350.student_echo.business;

import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.ReviewableItem;
import comp3350.student_echo.persistence.ReviewPersistence;

public class AccessReviews {
    private final ReviewPersistence reviewPersistence;

    public AccessReviews() {
        reviewPersistence = Services.getReviewPersistence(true);
    }

    public void addReview(Review r) {
        reviewPersistence.addReview(r);
    }

    public List<Review> getReviewsFor(ReviewableItem item) {
        if(item instanceof Course) return getReviewsFor((Course) item);
        else if(item instanceof Instructor) return getReviewsFor((Instructor) item);
        return null;
    }
    public List<Review> getReviewsFor(Course c) {
        return reviewPersistence.getReviewsFor(c);
    }

    public List<Review> getReviewsFor(Instructor inst) {
        return reviewPersistence.getReviewsFor(inst);
    }

    public void deleteReview(Review r) {
        reviewPersistence.deleteReview(r);
    }

    public boolean updateReviewInDatabase(Review updatedReview) {
        return reviewPersistence.updateReview(updatedReview);
    }
}
