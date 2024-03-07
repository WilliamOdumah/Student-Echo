package comp3350.student_echo.persistence.stubs;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.persistence.InstructorPersistence;

public class InstructorPersistenceStub implements InstructorPersistence {
    private static int nextInstID = 10000;
    private final Map<Integer, Instructor> instructorMap;

    public InstructorPersistenceStub() {
        this.instructorMap = new HashMap<>();
        addInstructor(new Instructor("Dr.", "Gary", "Chalmers"));
        addInstructor(new Instructor("Sir", "Selma", "Bouvier"));
        addInstructor(new Instructor("Instructor", "Arnie", "Pye"));
        addInstructor(new Instructor("Professor", "Mary", "Bailey"));
        addInstructor(new Instructor("Associated Professor", "Emily", "Davis"));
        addInstructor(new Instructor("Associated Professor", "Olivia", "Wilson"));
        addInstructor(new Instructor("Dean", "John", "Brown"));
    }

    @Override
    public List<Instructor> getInstructorSequential() {
        return Collections.unmodifiableList((List<Instructor>)instructorMap.values());
    }

    @Override
    public Instructor getInstructor(int instructorID) {
        return instructorMap.getOrDefault(instructorID, null);
    }

    @Override
    public void addInstructor(Instructor newInst){
        int id = nextInstID; nextInstID++;
        instructorMap.put(id, newInst);
        newInst.setID(id);
    }
}
