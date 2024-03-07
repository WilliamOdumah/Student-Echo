package comp3350.student_echo.tests.business.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.business.access.AccessCourses;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.ReviewableItem;
import comp3350.student_echo.persistence.CoursePersistence;
import comp3350.student_echo.persistence.hsqldb.CoursePersistenceHSQLDB;
import comp3350.student_echo.tests.utils.TestUtils;

public class AccessCoursesIT {

    private AccessCourses accessCourses;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final CoursePersistence persistence = new CoursePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));

        // inject real HSQLDB
        this.accessCourses = new AccessCourses(persistence);
    }
    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

    @Test
    public void testGetCourse() {
        Course c1 = new Course("CS", "COMP3010", "Distributed Computing");
        Course c2 = new Course("CS","COMP3380","Databases");

        assertTrue("retrieve first course by id", c1.equals(accessCourses.getCourse("COMP3010")));
        assertTrue("retrieve last course by id", c2.equals(accessCourses.getCourse("COMP3380")));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testGetCourses() {
        List<Course> courses = accessCourses.getCourses();
        Course c1 = new Course("CS", "COMP3010", "Distributed Computing");
        Course c2 = new Course("CS","COMP3380","Databases");

        assertTrue("ensure first course present in DB", c1.equals(courses.get(0)) );
        assertTrue("ensure last course present in DB", c2.equals(courses.get(3)));

        // should not be able to directly add to courses
        courses.add(new Course("CS.","COMP3010", "Distributed"));

        // should not be able to directly remove from courses
        courses.remove(0);
    }

    @Test(expected = InvalidCourseException.class)
    public void testAddCourse() {
        Course valid = new Course("CS", "NEW1010", "New Course");
        accessCourses.addCourse(valid);

        assertTrue("valid course should be added", accessCourses.getCourse("NEW1010").equals(valid));

        // invalid course should throw InvalidCourseException
        Course invalid = new Course("CS", "badcourseid5", "another course");
        accessCourses.addCourse(invalid);

        // null course should throw exception
        accessCourses.addCourse(null);
    }

    @Test
    public void testFilterCourses() {
        List <Course> courses = accessCourses.getCourses();

        String search = "3010";
        ArrayList<Course> filteredList = accessCourses.filterCourses(search, courses);
        assertEquals(1,filteredList.size());
        assertEquals("COMP3010", filteredList.get(0).getCourseID());

        String searchText = "2043";
        ArrayList<Course> filtered = accessCourses.filterCourses(searchText, courses);
        assertEquals(0,filtered.size());

        search = "D";
        ArrayList<Course> list= accessCourses.filterCourses(search.toLowerCase(), courses);
        assertEquals(3,list.size());
    }


    // ReviewableItem interface tests
    @Test
    public void testGetItems() {
        List<ReviewableItem> items = accessCourses.getItems();
        ReviewableItem item1 = new Course("CS", "COMP3010", "Distributed Computing");
        ReviewableItem item2 = new Course("CS","COMP3380","Databases");

        ReviewableItem expected1 = items.get(0);
        ReviewableItem expected2 = items.get(3);

        assertTrue("ensure first item present in DB",((Course)item1).equals((Course)items.get(0)) );
        assertTrue("ensure last item present in DB", ((Course)item2).equals((Course)items.get(3)) );
    }
    @Test
    public void testFilter() {
        List <ReviewableItem> items = accessCourses.getItems();

        String search = "3010";
        List<ReviewableItem> filteredList = accessCourses.filter(search, items);
        assertEquals(1,filteredList.size());
        assertEquals("COMP3010", ((Course)filteredList.get(0)).getCourseID());

        String searchText = "2043";
        List<ReviewableItem> filtered = accessCourses.filter(searchText, items);
        assertEquals(0,filtered.size());

        search = "D";
        List<ReviewableItem> list= accessCourses.filter(search.toLowerCase(), items);
        assertEquals(3,list.size());
    }

}
