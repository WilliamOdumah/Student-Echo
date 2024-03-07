package comp3350.student_echo.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.student_echo.business.access.AccessCourses;
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
    public void testFilterCourses() {

    }
    @Test
    public void testGetCourses() {

    }

    @Test
    public void testGetCourse() {

    }

    @Test
    public void testAddCourse() {

    }


    // ReviewableItem interface tests
    @Test
    public void testGetItems() {

    }
    @Test
    public void testFilter() {

    }

}
