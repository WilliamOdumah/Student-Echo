package comp3350.student_echo.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.ReviewableItem;
import comp3350.student_echo.persistence.InstructorPersistence;

public class AccessInstructors implements AccessReviewableItems {
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
            if (instructor.getFirstName().toLowerCase().contains(searchText.toLowerCase()) || instructor.getLastName().toLowerCase().contains(searchText.toLowerCase())){
                filteredInstructor.add(instructor);
            }
        }
        return filteredInstructor;
    }

    // TODO
    public Instructor getInstructor(int instructorID) {
        return instructorPersistence.getInstructor(instructorID);
    }

    @Override
    public List<ReviewableItem> getItems() {
        return getInstructors().stream().map(inst -> (ReviewableItem)inst).collect(Collectors.toList());
    }

    @Override
    public List<ReviewableItem> filter(String input, List<ReviewableItem> items) {
        List<Instructor> instructorItem = items.stream().map(item ->(Instructor)item).collect(Collectors.toList());
        List<Instructor> filteredInstructors = filterInstructor(input, instructorItem);
        return filteredInstructors.stream().map(inst -> (ReviewableItem)inst).collect(Collectors.toList());
    }
}
