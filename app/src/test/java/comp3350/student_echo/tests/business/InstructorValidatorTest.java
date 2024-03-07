package comp3350.student_echo.tests.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.business.InstructorValidator;
import comp3350.student_echo.business.access.AccessInstructors;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.persistence.InstructorPersistence;
import comp3350.student_echo.persistence.stubs.InstructorPersistenceStub;


public class InstructorValidatorTest {
    private InstructorPersistence stub = new InstructorPersistenceStub();
    private AccessInstructors access;

    @Before
    public void setup() {
        stub = new InstructorPersistenceStub();
        access = new AccessInstructors(stub);
    }

    @Test
    public void testInstructorExists() {
        Instructor i1 = new Instructor("Dr.", "Gary", "Chalmers");
        Instructor i2 = new Instructor("Does","Not","Exist");
        assertTrue("This instructor exists in stub", InstructorValidator.instructorExists(i1, access));
        assertFalse("This instructor does not exists in stub", InstructorValidator.instructorExists(i2, access));
    }

    @Test
    public void testValidName() {
        assertFalse("no numbers allowed", InstructorValidator.validName("1234"));
        assertFalse("only allow single period at end", InstructorValidator.validName("a.b.c."));
        assertTrue("only allow single period at end", InstructorValidator.validName("Dr."));
        assertFalse("cannot be null", InstructorValidator.validName(null));
        assertFalse("cannot be spaces", InstructorValidator.validName("       "));
        assertTrue("allow hyphens and apostrophies", InstructorValidator.validName("Johns'-Smith"));
        assertTrue("basic name", InstructorValidator.validName("Burt"));
    }

    // validateInstructor
    @Test(expected = InvalidInstructorException.class)
    public void testValidateInstructorAlreadyExisting() {
        Instructor duplicate = new Instructor("Dr.", "Gary", "Chalmers");

        // throws exception
        InstructorValidator.validateInstructor(duplicate, stub);
    }

    @Test(expected = InvalidInstructorException.class)
    public void testValidateInstructorBadName() {
        Instructor bad = new Instructor("Dr.", "    ", "    ");

        // throws exception
        InstructorValidator.validateInstructor(bad, stub);
    }
    @Test()
    public void testValidateInstructorValid() {
        Instructor toAdd = new Instructor("Dr.", "Yo", "Hellooooo");

        // no exception
        InstructorValidator.validateInstructor(toAdd, stub);
    }
}
