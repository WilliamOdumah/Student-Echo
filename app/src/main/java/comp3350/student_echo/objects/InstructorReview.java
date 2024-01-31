package comp3350.student_echo.objects;

public class InstructorReview extends Review {
    Instructor instructor;

    public InstructorReview(Instructor instructor, String comment, int overallRating, int difficultyRating) {
        super(comment, overallRating, difficultyRating);
        this.instructor = instructor;
    }
}
