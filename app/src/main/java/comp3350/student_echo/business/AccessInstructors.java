package comp3350.student_echo.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.persistence.InstructorPersistence;

public class AccessInstructors {
	private final InstructorPersistence instructorPersistence;

	public AccessInstructors() {
		instructorPersistence = Services.getInstructorPersistence(true);
	}

    public List<Instructor> getInstructors() {
        List<Instructor> instructors = instructorPersistence.getInstructorSequential();
        return Collections.unmodifiableList(instructors);
    }

    public ArrayList<Instructor> filterInstructor(String searchText , List<Instructor> instructorList) {

        ArrayList<Instructor> filteredInstructor = new ArrayList<>();

        for (Instructor instructor : instructorList){
            if (instructor.getFirstName().toLowerCase().contains(searchText) || instructor.getLastName().toLowerCase().contains(searchText)){
                filteredInstructor.add(instructor);
            }
        }
        return filteredInstructor;
    }
}
