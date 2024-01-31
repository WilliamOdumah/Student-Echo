package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.persistence.InstructorPersistence;

public class InstructorPersistenceStub implements InstructorPersistence {
    private List<Instructor> instructors;

    public InstructorPersistenceStub() {
        this.instructors = new ArrayList<>();

        instructors.add(new Instructor("Dr.", "Gary", "Chalmers"));
        instructors.add(new Instructor("Sir", "Selma", "Bouvier"));
        instructors.add(new Instructor("Instructor", "Arnie", "Pye"));
        instructors.add(new Instructor("Professor", "Mary", "Bailey"));
    }

    @Override
    public List<Instructor> getInstructorSequential() {
        return Collections.unmodifiableList(instructors);
    }

    @Override
    public Instructor insertInstructor(Instructor currentStudent) {
        // don't bother checking for duplicates
        instructors.add(currentStudent);
        return currentStudent;
    }

    @Override
    public Instructor updateInstructor(Instructor currentInstructor) {
        int index;

        index = instructors.indexOf(currentInstructor);
        if (index >= 0)
        {
            instructors.set(index, currentInstructor);
        }
        return currentInstructor;
    }

    @Override
    public void deleteInstructor(Instructor currentInstructor) {
        int index;

        index = instructors.indexOf(currentInstructor);
        if (index >= 0)
        {
            instructors.remove(index);
        }
    }
}
