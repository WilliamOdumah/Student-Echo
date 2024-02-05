package comp3350.student_echo.objects;

public class CourseReview extends Review {
    Course course;

    public CourseReview(Course course, String comment, float overallRating, float difficultyRating) {
        super(comment, overallRating, difficultyRating);
        this.course = course;
    }
}
