package comp3350.student_echo.objects;

public class InstructorReview extends Review {
    private Instructor instructor;

    public InstructorReview(Instructor instructor, String comment, int overallRating, int difficultyRating, StudentAccount by) {
        super(comment, overallRating, difficultyRating, by);
        this.instructor = instructor;
    }

    public Instructor getInstructor() {return instructor;}
}
