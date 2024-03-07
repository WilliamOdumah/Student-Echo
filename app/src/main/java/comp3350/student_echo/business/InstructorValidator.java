package comp3350.student_echo.business;

import java.util.List;
import java.util.regex.Pattern;

import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.business.access.AccessInstructors;
import comp3350.student_echo.objects.reviewableItems.Instructor;


public class InstructorValidator {
    static AccessInstructors accessInstructors;
    public static void validateInstructor(Instructor inst) throws InvalidInstructorException {
        accessInstructors = new AccessInstructors();
        if(instructorExists(inst)) {
            throw new InvalidInstructorException(
                    String.format("Instructor %s %s %s already exists!", inst.getTitle(),
                            inst.getFirstName(), inst.getLastName())
            );
        }
        else if( !validName(inst.getFirstName()) ) {
            throw new InvalidInstructorException( String.format("First name %s not valid", inst.getFirstName()) );
        } else if( !validName(inst.getLastName()) ) {
            throw new InvalidInstructorException( String.format("Last name %s not valid", inst.getLastName()) );
        } else if( !validName(inst.getTitle()) ) {
            throw new InvalidInstructorException( String.format("Title %s not valid", inst.getTitle()) );
        }
    }

    private static boolean instructorExists(Instructor inst) {
        List<Instructor> allInstructors = accessInstructors.getInstructors();
        for(Instructor cur : allInstructors) {
            if(cur.equals(inst)){
                return true;
            }
        }
        return false;
    }

    private static boolean validName(String name){
        // cannot be null and must contain a character
        if(name == null || name.trim().isEmpty()) return false;
        // Regular expression to match alphabetic characters, spaces, hyphens, and apostrophes
        String pattern = "^[a-zA-ZÀ-ÿ'\\- ]+[.]?$";
        return Pattern.matches(pattern, name);
    }

}
