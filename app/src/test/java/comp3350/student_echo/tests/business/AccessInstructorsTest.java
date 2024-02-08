package comp3350.student_echo.tests.business;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

import comp3350.student_echo.business.AccessInstructors;
import comp3350.student_echo.objects.Instructor;

public class AccessInstructorsTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testGetInstructors() {
        AccessInstructors accessInstructors = new AccessInstructors();
        List<Instructor> instructors = accessInstructors.getInstructors();
        Instructor i1 = new Instructor("Dr.", "Gary", "Chalmers");

        assertTrue("ensure instructor present in stub", i1.equals(instructors.get(0)));

        // should not be able to directly add to instructors
        instructors.add(new Instructor("Dr.","Joe", "Doe"));

        // should not be able to directly remove from instructors
        instructors.remove(0);
    }
}