package comp3350.student_echo.persistence;

import java.util.List;
import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.objects.reviewableItems.Instructor;

public interface InstructorPersistence {
    List<Instructor> getInstructorSequential();
    Instructor getInstructor(int instructorID);
    void addInstructor(Instructor newInst);
}
