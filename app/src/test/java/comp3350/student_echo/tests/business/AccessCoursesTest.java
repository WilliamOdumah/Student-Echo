package comp3350.student_echo.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    public void testListCourses() {

        final Course course;
        course = accessCourses.getSequential();

        assertNotNull("first sequential course should not be null", course);

        assertTrue("COMP1020".equals(course.getCourseID()));

        System.out.println("Finished test AccessCourses\n");
    }

    @Test
    public void testGetCourses() {
        final List<Course> courses = accessCourses.getCourses();
        assertEquals(10, courses.size());
        System.out.println("Finished test Get Courses\n");
    }

    @Test
    public void testDeleteCourse() {
        final Course c = accessCourses.getSequential();
        List<Course> courses = accessCourses.getCourses();
        assertEquals(11, courses.size());
        accessCourses.deleteCourse(c);
        courses = accessCourses.getCourses();
        assertEquals(10, courses.size());
        System.out.println("Finished test Delete Course\n");
    }

    @Test
    public void testInsertCourse() {
        final Course c = new Course("Business", "ACC 1100", "Introductory Financial Accounting");
        accessCourses.insertCourse(c);
        assertEquals(11, accessCourses.getCourses().size());

        System.out.println("Finished test Insert Course\n");
    }

    @Test
    public void testUpdateCourse() {
        final Course c = accessCourses.getSequential();
        final Course u = new Course(c.getDepartment(),c.getCourseID(), "A new name");
        accessCourses.updateCourse(u);
        assertEquals(11, accessCourses.getCourses().size());
        System.out.println("Finished test Update Course\n");
    }
}
