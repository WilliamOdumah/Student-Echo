package comp3350.student_echo.objects;

public class CourseReview extends Review {
    Course course;

    public CourseReview(Course course, String comment, int overallRating, int difficultyRating, StudentAccount by) {
        super(comment, overallRating, difficultyRating, by);
        this.course = course;
    }

    public Course getCourse() {return course;}
}
