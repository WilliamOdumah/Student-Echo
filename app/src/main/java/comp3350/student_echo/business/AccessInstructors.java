package comp3350.student_echo.business;

import java.util.Collections;
import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.persistence.CoursePersistence;
import comp3350.student_echo.persistence.InstructorPersistence;

public class AccessInstructors
{
	private final InstructorPersistence instructorPersistence;

	public AccessInstructors() {
		instructorPersistence = Services.getInstructorPersistence();
	}

    public AccessInstructors(InstructorPersistence instructorPersistence){
        this.instructorPersistence = instructorPersistence;
    }

    public List<Instructor> getInstructors() {
        List<Instructor> instructors = instructorPersistence.getInstructorSequential();
        return Collections.unmodifiableList(instructors);
    }


}
