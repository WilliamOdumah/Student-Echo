package comp3350.student_echo.business.Exceptions;

public class InvalidInstructorException extends RuntimeException {
    public InvalidInstructorException(String error) {
        super("Invalid instructor field:\n" + error);
    }
}
