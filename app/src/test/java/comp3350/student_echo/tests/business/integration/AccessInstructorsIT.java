package comp3350.student_echo.tests.business.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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
    private Instructor i1;
    private Instructor i2;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final InstructorPersistence hsqldb = new InstructorPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));

        // inject real HSQLDB
        accessInstructors = new AccessInstructors(hsqldb);

        // setup
        i1 = new Instructor("first", "add", "this");
        i2 = new Instructor("second","yo","wo");
        accessInstructors.addInstructor(i1);
        accessInstructors.addInstructor(i2);

    }
    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

    @Test
    public void testGetInstructor() {
        int id1 = i1.getInstructorID();
        int id2 = i2.getInstructorID();

        assertTrue("retrieve instructor by id", i1.equals(accessInstructors.getInstructor(id1)) );
        assertTrue("retrieve last instructor by id", i2.equals(accessInstructors.getInstructor(id2)) );
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetInstructors() {
        List<Instructor> instructors = accessInstructors.getInstructors();

        Instructor last = instructors.get(instructors.size()-1);

        assertTrue("ensure we can access instructor in db", last.equals(i2));

        // should not be able to directly add to instructors
        instructors.add(new Instructor("Dr.", "Joe", "Doe"));

        // should not be able to directly remove from instructors
        instructors.remove(0);
    }

    @Test(expected =InvalidInstructorException.class)
    public void testAddInstructor() {
        Instructor valid = new Instructor("Dr.", "Yo", "WOOOOOO");
        accessInstructors.addInstructor(valid);

        assertTrue("valid instructor should be added", accessInstructors.getInstructor(valid.getInstructorID()).equals(valid));

        // now try adding again (duplicate addition)
        // invalid instructor should throw InvalidInstructorException
        accessInstructors.addInstructor(valid);

        // null course should throw exception
        accessInstructors.addInstructor(null);
    }

    @Test
    public void testFilterInstructor() {
        List <ReviewableItem> items = accessInstructors.getItems();

        String search = "yo";
        List<ReviewableItem> filteredList = accessInstructors.filter(search, items);
        assertEquals(1,filteredList.size());
        assertEquals("wo", ((Instructor)filteredList.get(0)).getLastName());

        String searchText = "lollllll";
        List<ReviewableItem> filtered = accessInstructors.filter(searchText, items);
        assertEquals(0,filtered.size());

        search = "add";
        List<ReviewableItem> list= accessInstructors.filter(search.toLowerCase(), items);
        assertEquals(1,list.size());
    }

    // ReviewableItem interface testing
    @Test
    public void testGetItems() {
        ReviewableItem item1 = new Instructor("this", "is", "new");
        accessInstructors.addInstructor((Instructor)item1);
        List<ReviewableItem> items = accessInstructors.getItems();

        assertTrue("ensure first item present in DB",((Instructor)item1).equals((Instructor)items.get(items.size()-1)) );

    }
    @Test
    public void testFilter() {
        List <ReviewableItem> items = accessInstructors.getItems();

        String search = "yo";
        List<ReviewableItem> filteredList = accessInstructors.filter(search, items);
        assertEquals(1,filteredList.size());
        assertEquals("wo", ((Instructor)filteredList.get(0)).getLastName());

        String searchText = "lollllll";
        List<ReviewableItem> filtered = accessInstructors.filter(searchText, items);
        assertEquals(0,filtered.size());

        search = "add";
        List<ReviewableItem> list= accessInstructors.filter(search.toLowerCase(), items);
        assertEquals(1,list.size());
    }

}
