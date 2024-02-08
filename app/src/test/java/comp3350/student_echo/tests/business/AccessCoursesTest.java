package comp3350.student_echo.tests.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.AccessCourses;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.persistence.CoursePersistence;



public class AccessCoursesTest {
    private AccessCourses accessCourses;

    @Before
    public void setUp() {
        CoursePersistence coursePersistence = Services.getCoursePersistence();
        accessCourses = new AccessCourses(coursePersistence);
    }

    @Test
    public void testGetCourses() {
        final List<Course> courses = accessCourses.getCourses();
        assertEquals(10, courses.size());
        System.out.println("Finished test Get Courses\n");
    }
}
