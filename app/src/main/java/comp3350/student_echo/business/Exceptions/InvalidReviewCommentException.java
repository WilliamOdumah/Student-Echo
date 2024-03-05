package comp3350.student_echo.business.Exceptions;

public class InvalidReviewCommentException extends RuntimeException{
    public InvalidReviewCommentException(String error) {
        super("Invalid comment in review:\n" + error);
    }
}
