package comp3350.student_echo.tests.business;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

import comp3350.student_echo.business.AccessCourses;
import comp3350.student_echo.objects.Course;

public class AccessCoursesTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCourses() {
        AccessCourses accessCourses = new AccessCourses();
        List<Course> courses = accessCourses.getCourses();
        Course c1 = new Course("CS", "COMP1010", "IntroComp");

        assertTrue("ensure course present in stub", c1.equals(courses.get(0)));

        // should not be able to directly add to courses
        courses.add(new Course("CS.","COMP3010", "Distributed"));

        // should not be able to directly remove from courses
        courses.remove(0);

    }
}
