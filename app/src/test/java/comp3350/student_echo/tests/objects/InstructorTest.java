package comp3350.student_echo.tests.objects;

import org.junit.Test;

import comp3350.student_echo.objects.Instructor;
import static org.junit.Assert.*;

public class InstructorTest {

    @Test
    public void testInstructorCreation() {
        Instructor i1 = new Instructor("Dr.", "John", "Smith");

        assertEquals("Check title", i1.getTitle(), "Dr.");
        assertEquals("Check first name", i1.getFirstName(), "John");
        assertEquals("Check last name", i1.getLastName(), "Smith");
    }

}
