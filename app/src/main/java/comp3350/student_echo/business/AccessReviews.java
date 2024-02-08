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

    public void addCourseReview(CourseReview review) {
        reviewPersistence.addCourseReview(review);
    }

    public void addInstructorReview(InstructorReview review) {
        reviewPersistence.addInstructorReview(review);
    }

    public List<CourseReview> getReviewFor(Course c) {
        return reviewPersistence.getReviewsFor(c);
    }

    public List<InstructorReview> getReviewFor(Instructor inst) {
        return reviewPersistence.getReviewsFor(inst);
    }

    public CourseReview getCourseReviewById(String reviewId) {
        return  reviewPersistence.getCourseReviewById(reviewId);
    }

    public InstructorReview getInstructorReviewById(String reviewId) {
        return  reviewPersistence.getInstructorReviewById(reviewId);
    }

    public void deleteCourseReview(String reviewId) {
        reviewPersistence.deleteCourseReview(reviewId);
    }

    public void deleteInstructorReview(String reviewId) {
        reviewPersistence.deleteInstructorReview(reviewId);
    }

    public boolean updateCourseReviewInDatabase(CourseReview updatedReview) {
        return reviewPersistence.updateCourseReviewInDatabase(updatedReview);
    }

    public boolean updateInstructorReviewInDatabase(InstructorReview updatedReview) {
        return reviewPersistence.updateInstructorReviewInDatabase(updatedReview);
    }

}
