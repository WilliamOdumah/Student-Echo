package comp3350.student_echo.business;

import java.util.List;
import java.util.regex.Pattern;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.business.access.AccessInstructors;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.persistence.InstructorPersistence;


public class InstructorValidator {

    // default call
    public static void validateInstructor(Instructor inst) throws InvalidInstructorException {
        validateInstructor(inst, Services.getInstructorPersistence());
    }

    // Dependency Injection call
    public static void validateInstructor(Instructor inst, InstructorPersistence p){
        AccessInstructors accessInstructors = new AccessInstructors(p);
        if(instructorExists(inst, accessInstructors)) {
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

    public static boolean instructorExists(Instructor inst, AccessInstructors access) {
        List<Instructor> allInstructors = access.getInstructors();
        for(Instructor cur : allInstructors) {
            if(cur.equals(inst)){
                return true;
            }
        }
        return false;
    }

    public static boolean validName(String name){
        // cannot be null and must contain a character
        if(name == null || name.trim().isEmpty()) return false;
        // Regular expression to match alphabetic characters, spaces, hyphens, and apostrophes
        String pattern = "^[a-zA-ZÀ-ÿ'\\- ]+[.]?$";
        return Pattern.matches(pattern, name);
    }

}
