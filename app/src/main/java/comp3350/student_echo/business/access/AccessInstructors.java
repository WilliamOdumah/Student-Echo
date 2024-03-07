package comp3350.student_echo.business.access;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.business.InstructorValidator;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.objects.reviewableItems.ReviewableItem;
import comp3350.student_echo.persistence.InstructorPersistence;

public class AccessInstructors implements AccessReviewableItems, Serializable {
	private InstructorPersistence instructorPersistence;

	public AccessInstructors() {
		instructorPersistence = Services.getInstructorPersistence();
	}
    public AccessInstructors(final InstructorPersistence persistence) {
        instructorPersistence = persistence;
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

    public Instructor getInstructor(int instructorID) {
        return instructorPersistence.getInstructor(instructorID);
    }

    // default call
    public void addInstructor(Instructor newInst) throws InvalidInstructorException {
        addInstructor(newInst, instructorPersistence);
    }
    // dependency injection call
    public void addInstructor(Instructor newInst, InstructorPersistence p) throws InvalidInstructorException {
        InstructorValidator.validateInstructor(newInst, p); // throws InvalidInstructorException (upon invalid fields or dup)
        instructorPersistence.addInstructor(newInst);
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
