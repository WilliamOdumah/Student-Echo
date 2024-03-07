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

import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.business.access.AccessInstructors;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.objects.reviewableItems.ReviewableItem;
import comp3350.student_echo.persistence.InstructorPersistence;
import comp3350.student_echo.persistence.hsqldb.InstructorPersistenceHSQLDB;
import comp3350.student_echo.tests.utils.TestUtils;

public class AccessInstructorsIT {

    private AccessInstructors accessInstructors;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final InstructorPersistence hsqldb = new InstructorPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));

        // inject real HSQLDB
        accessInstructors = new AccessInstructors(hsqldb);
    }
    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

    @Test
    public void testGetInstructor() {
        Instructor i1 = new Instructor("Professor", "John", "Doe");
        Instructor i2 = new Instructor("Dr.","Suzanne","Walker");

        assertTrue("retrieve first instructor by id", i1.equals(accessInstructors.getInstructor(1)) );
        assertTrue("retrieve last instructor by id", i2.equals(accessInstructors.getInstructor(4)) );
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetInstructors() {
        List<Instructor> instructors = accessInstructors.getInstructors();
        Instructor i1 = new Instructor("Professor", "John", "Doe");

        assertTrue("ensure instructor present in db", i1.equals(instructors.get(0)));

        // should not be able to directly add to instructors
        instructors.add(new Instructor("Dr.", "Joe", "Doe"));

        // should not be able to directly remove from instructors
        instructors.remove(0);
    }

    @Test(expected =InvalidInstructorException.class)
    public void testAddInstructor() {
        Instructor valid = new Instructor("Dr.", "Yo", "WOOOOOO");
        accessInstructors.addInstructor(valid);

        assertTrue("valid instructor should be added", accessInstructors.getInstructor(5).equals(valid));

        // invalid instructor should throw InvalidInstructorException
        Instructor duplicate = new Instructor("Professor", "John", "Doe");
        accessInstructors.addInstructor(duplicate);

        // null course should throw exception
        accessInstructors.addInstructor(null);
    }

    @Test
    public void testFilterInstructor() {
        List<Instructor> instructors = accessInstructors.getInstructors();

        String search = "doe";
        ArrayList<Instructor> filteredList = accessInstructors.filterInstructor(search, instructors);
        assertEquals(2, filteredList.size());
        assertEquals("John", filteredList.get(0).getFirstName());
        assertEquals("Jane", filteredList.get(1).getFirstName());

        String searchText = "peter";
        ArrayList<Instructor> filtered = accessInstructors.filterInstructor(searchText, instructors);
        assertEquals(1, filtered.size());
        assertEquals("Smith", filtered.get(0).getLastName());
    }

    // ReviewableItem interface testing
    @Test
    public void testGetItems() {
        List<ReviewableItem> items = accessInstructors.getItems();
        ReviewableItem item1 = new Instructor("Professor", "John", "Doe");
        ReviewableItem item2 = new Instructor("Dr.","Suzanne","Walker");

        assertTrue("ensure first item present in DB",((Instructor)item1).equals((Instructor)items.get(0)) );
        assertTrue("ensure last item present in DB", ((Instructor)item2).equals((Instructor)items.get(3)) );
    }
    @Test
    public void testFilter() {
        List <ReviewableItem> items = accessInstructors.getItems();

        String search = "Doe";
        List<ReviewableItem> filteredList = accessInstructors.filter(search, items);
        assertEquals(2,filteredList.size());
        assertEquals("Doe", ((Instructor)filteredList.get(0)).getLastName());

        String searchText = "lollllll";
        List<ReviewableItem> filtered = accessInstructors.filter(searchText, items);
        assertEquals(0,filtered.size());

        search = "J";
        List<ReviewableItem> list= accessInstructors.filter(search.toLowerCase(), items);
        assertEquals(2,list.size());
    }

}
