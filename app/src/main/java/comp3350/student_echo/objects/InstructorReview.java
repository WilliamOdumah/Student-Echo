package comp3350.student_echo.objects;

public class InstructorReview extends Review {
    private Instructor instructor;
    private static int nextInstructorReviewID = 10;

    public InstructorReview(Instructor instructor, String comment, int overallRating, int difficultyRating, StudentAccount by) {
        super(nextInstructorReviewID++, comment, overallRating, difficultyRating, by);
        this.instructor = instructor;
    }
    public InstructorReview(int uid, Instructor instructor, String comment, int overallRating, int difficultyRating, StudentAccount by)  {
        super(uid, comment, overallRating, difficultyRating, by);
        this.instructor = instructor;
    }

    public Instructor getInstructor() {return instructor;}
}
