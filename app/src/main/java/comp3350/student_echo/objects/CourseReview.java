package comp3350.student_echo.objects;

public class CourseReview extends Review {
    Course course;

    public CourseReview(Course course, String comment, int overallRating, int difficultyRating) {
        super(comment, overallRating, difficultyRating);
        this.course = course;
    }
}
