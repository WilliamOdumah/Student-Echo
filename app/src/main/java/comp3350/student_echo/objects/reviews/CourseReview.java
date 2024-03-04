package comp3350.student_echo.objects.reviews;

import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.StudentAccount;

public class CourseReview extends Review {
    private Course course;
    private static int nextCourseReviewID = 10;

    public CourseReview(Course course, String comment, int overallRating, int difficultyRating, StudentAccount by) {
        super(nextCourseReviewID++, comment, overallRating, difficultyRating, by);
        this.course = course;
    }
    public CourseReview(int uid, Course course, String comment, int overallRating, int difficultyRating, StudentAccount by) {
        super(uid, comment, overallRating, difficultyRating,by);
        this.course = course;
    }

    public Course getCourse() { return course; }
}
