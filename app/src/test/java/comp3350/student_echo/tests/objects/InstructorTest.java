package comp3350.student_echo.tests.objects;

import org.junit.Test;

import comp3350.student_echo.objects.Instructor;
import static org.junit.Assert.*;

public class InstructorTest {

    @Test
    public void testInstructor()
    {
        Instructor instructor;

        System.out.println("\nStarting testInstructor");

        instructor = new Instructor("LOL", "12345", "");
        assertNotNull(instructor);
        assertTrue("12345".equals(instructor.getFirstName()));
        assertTrue("Software Development".equals(instructor.getLastName()));

        System.out.println("Finished testInstructor");
    }

}
