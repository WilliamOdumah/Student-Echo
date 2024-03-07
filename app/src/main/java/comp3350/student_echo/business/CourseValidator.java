package comp3350.student_echo.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.objects.reviewableItems.Course;


public class CourseValidator {
    public static void validateCourse(Course c) throws InvalidCourseException{

        if( !validName(c.getCourseName()) ) {
            throw new InvalidCourseException("Course name not valid");
        } else if( !validCourseIDFormat(c.getCourseID()) ) {
            throw new InvalidCourseException(
                    String.format("CourseID: %s not valid", c.getCourseID())
            );
        }
    }

    //verify course ID format, minimum 7 characters, maximum 8 characters
    //courseID cannot contain space and has to be combination of letters and numbers -- 3 or 4 letters at beginning then 4 numbers
    public static boolean validCourseIDFormat(String courseID){
        //valid course length is either 7 or 8 (e.g: ACC1100 or COMP1010)
        if (courseID.length() == 7 || courseID.length() == 8 ) {
            if (courseID.contains(" ")){
                System.out.println("Invalid Course ID data");
                return false;
            }else{
                //check format combination of  3 or 4 letters, and 4 numbers
                String regex = "^[A-Za-z]{3,4}\\d{4}$";

                Pattern pattern = Pattern.compile(regex);

                // Create a matcher with the input courseID
                Matcher matcher = pattern.matcher(courseID);

                // Return true if the input courseID match the pattern
                return matcher.matches();
            }
        }
        System.out.println("Invalid Course ID length");
        return false;
    }

    public static boolean validName(String name){
        // cannot be null and must contain a character
        return name != null && !name.trim().isEmpty() ;
    }

}
