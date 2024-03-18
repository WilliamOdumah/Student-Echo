package comp3350.student_echo.business.Exceptions;

public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException(String error) {
        super("Invalid account field:\n" + error);
    }
}