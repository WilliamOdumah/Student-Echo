package comp3350.student_echo.business.Exceptions;

public class InvalidCourseException extends RuntimeException{
    public InvalidCourseException(String error) {
        super("Invalid course field:\n" + error);
    }
}
