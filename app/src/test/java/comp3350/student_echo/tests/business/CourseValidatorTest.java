package comp3350.student_echo.tests.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import comp3350.student_echo.business.CourseValidator;
import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.objects.reviewableItems.Course;


public class CourseValidatorTest {

    @Test()
    public void testValidateCourseValid() {
        Course c1 = new Course("CS", "COMP9999", "Mid Comp");

        // no exception
        CourseValidator.validateCourse(c1);
    }

    @Test(expected = InvalidCourseException.class)
    public void testValidateCourseInvalid() {
        Course c1 = new Course("CS", "NOTVALIDID", "name");
        Course c2 = new Course("CS", "VALD110", "");

        // throws exception
        CourseValidator.validateCourse(c1);
        CourseValidator.validateCourse(c1);
    }

    @Test
    public void testValidateCourseIDFormat() {
        //verify course ID format, minimum 7 characters, maximum 8 characters
        //courseID cannot contain space and has to be combination of letters and numbers -- 3 or 4 letters at beginning then 4 numbers

        assertFalse("less than 7 characters", CourseValidator.validCourseIDFormat("AA500"));
        assertFalse("more than 8 characters", CourseValidator.validCourseIDFormat("AAAAA55555"));
        assertFalse("only letters", CourseValidator.validCourseIDFormat("AAAAAAAA"));
        assertFalse("only numbers", CourseValidator.validCourseIDFormat("12345678"));
        assertTrue("valid entry size 7", CourseValidator.validCourseIDFormat("aaa1234"));
        assertTrue("valid entry size 8", CourseValidator.validCourseIDFormat("aaaa1234"));
    }
    @Test
    public void testValidName() {
        assertFalse("must be non null", CourseValidator.validName(null));
        assertFalse("must be non empty", CourseValidator.validName("      "));
        assertTrue("valid name", CourseValidator.validName("hello"));
    }
}
