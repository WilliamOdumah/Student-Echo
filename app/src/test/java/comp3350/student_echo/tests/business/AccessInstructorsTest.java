package comp3350.student_echo.tests.business;

import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.AccessInstructors;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.Instructor;

import comp3350.student_echo.business.AccessInstructors;

import comp3350.student_echo.objects.Instructor;

import comp3350.student_echo.persistence.InstructorPersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;


public class AccessInstructorsTest {

    private AccessInstructors accessInstructors;

    @Before
    public void setUp(){
        InstructorPersistence instructorPersistence = Services.getInstructorPersistence();
        accessInstructors = new AccessInstructors(instructorPersistence);
    }

    @Test
    public void testListInstructor() {

        final Instructor instructor;
        instructor = accessInstructors.getSequential();

        assertNotNull("first sequential instructor should not be null", instructor);

        assertTrue("Gary".equals(instructor.getFirstName()));

        System.out.println("Finished test Instructor list\n");
    }

    @Test
    public void testGetInstructors() {
        final List<Instructor> instructors = accessInstructors.getInstructors();
        assertEquals(7, instructors.size());
        System.out.println("Finished test Get Instructors\n");
    }

    @Test
    public void testDeleteInstructor() {
        final Instructor c = accessInstructors.getSequential();
        List<Instructor> instructors = accessInstructors.getInstructors();
        assertEquals(7, instructors.size());
        accessInstructors.deleteInstructor(c);
        instructors = accessInstructors.getInstructors();

        assertEquals(6, instructors.size());
        System.out.println("Finished test Delete Instructor\n");
    }

    @Test
    public void testInsertInstructor() {
        final Instructor instructor = new Instructor("Dr.", "Jessica", "William");
        accessInstructors.insertInstructor(instructor);
        assertEquals(7, accessInstructors.getInstructors().size());

        System.out.println("Finished test Insert Instructor\n");
    }

    @Test
    public void testUpdateInstructor() {
        final Instructor instructor1= accessInstructors.getSequential();
        final Instructor instructor2 = new Instructor("new title",instructor1.getFirstName(), instructor1.getLastName());
        accessInstructors.updateInstructor(instructor2);
        assertEquals(6, accessInstructors.getInstructors().size());
        System.out.println("Finished test Update Instructor\n");
    }

}


