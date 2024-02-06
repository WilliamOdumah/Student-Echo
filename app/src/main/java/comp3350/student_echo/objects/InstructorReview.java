package comp3350.student_echo.objects;

public class InstructorReview extends Review {
    Instructor instructor;

    public InstructorReview(Instructor instructor, String comment, float overallRating, float difficultyRating) {
        super(comment, overallRating, difficultyRating);
        this.instructor = instructor;
    }

    public Instructor getInstructor() {return instructor;}
}
