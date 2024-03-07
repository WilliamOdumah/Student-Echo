package comp3350.student_echo.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.business.access.AccessCourses;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.persistence.CoursePersistence;
import comp3350.student_echo.persistence.stubs.CoursePersistenceStub;

public class AccessCoursesTest {

    private AccessCourses accessCourses;
    @Before
    public void setup() {
        final CoursePersistence stub = new CoursePersistenceStub();
        accessCourses = new AccessCourses(stub);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCourses() {
        List<Course> courses = accessCourses.getCourses();
        Course c1 = new Course("CS", "COMP1010", "IntroComp");

        assertTrue("ensure course present in stub", c1.equals(courses.get(0)));

        // should not be able to directly add to courses
        courses.add(new Course("CS.","COMP3010", "Distributed"));

        // should not be able to directly remove from courses
        courses.remove(0);
    }

    @Test
    public void testFilterCourses() {
        AccessCourses accessCourses = new AccessCourses();
        List <Course> courses = accessCourses.getCourses();

        String search = "3010";
        ArrayList<Course> filteredList = accessCourses.filterCourses(search, courses);
        assertEquals(1,filteredList.size());
        assertEquals("COMP3010", filteredList.get(0).getCourseID());

        String searchText = "2043";
        ArrayList<Course> filtered = accessCourses.filterCourses(searchText, courses);
        assertEquals(0,filtered.size());

        search = "Da";
        ArrayList<Course> list= accessCourses.filterCourses(search.toLowerCase(), courses);
        assertEquals(2,list.size());

    }

}
