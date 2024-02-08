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
        instructors.add(new Instructor("Associated Professor", "Emily", "Davis"));
        instructors.add(new Instructor("Associated Professor", "Olivia", "Wilson"));
        instructors.add(new Instructor("Dean", "John", "Brown"));
    }

    @Override
    public List<Instructor> getInstructorSequential() {
        return Collections.unmodifiableList(instructors);
    }
}
