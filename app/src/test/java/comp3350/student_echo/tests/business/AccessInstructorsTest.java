package comp3350.student_echo.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.business.access.AccessInstructors;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.objects.reviewableItems.ReviewableItem;
import comp3350.student_echo.persistence.InstructorPersistence;
import comp3350.student_echo.persistence.stubs.InstructorPersistenceStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class AccessInstructorsTest {

    private InstructorPersistence stub;
    private AccessInstructors accessInstructors;
    private Instructor inst;

    @Before
    public void setup() {
        stub = new InstructorPersistenceStub();
        accessInstructors = new AccessInstructors(stub);
        inst = new Instructor("Dr.", "Gary", "Chalmers");
    }
    @After
    public void destroy() {
        InstructorPersistenceStub.resetID();
    }

    @Test
    public void testGetInstructor() {
        Instructor i2 = new Instructor("Professor","Robert","Guderian");

        Instructor e1 = accessInstructors.getInstructor(1);
        Instructor e2 = accessInstructors.getInstructor(8);

        assertTrue("retrieve first instructor by id", inst.equals(e1) );
        assertTrue("retrieve last instructor by id", i2.equals(e2) );
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetInstructors() {
        List<Instructor> instructors = accessInstructors.getInstructors();
        Instructor expected = accessInstructors.getInstructor(1);

        assertTrue("ensure instructor present in stub", inst.equals(expected));

        // should not be able to directly add to instructors
        instructors.add(new Instructor("Dr.", "Joe", "Doe"));

        // should not be able to directly remove from instructors
        instructors.remove(0);
    }

    @Test(expected = InvalidInstructorException.class)
    public void testAddInstructor() {
        Instructor valid = new Instructor("Dr.", "Yo", "WOOOOOO");
        accessInstructors.addInstructor(valid, stub);
        Instructor expected = accessInstructors.getInstructor(9);

        assertTrue("valid instructor should be added", valid.equals(expected));

        // invalid instructor should throw InvalidInstructorException
        Instructor duplicate = new Instructor("Dr.", "Yo", "WOOOOOO");
        accessInstructors.addInstructor(duplicate, stub);

        // null course should throw exception
        accessInstructors.addInstructor(null, stub);
    }

    @Test
    public void testFilterInstructor() {
        List<Instructor> instructors = accessInstructors.getInstructors();

        String search = "ar";
        ArrayList<Instructor> filteredList = accessInstructors.filterInstructor(search, instructors);
        assertEquals(3, filteredList.size());
        assertEquals("Gary", filteredList.get(0).getFirstName());
        assertEquals("Arnie", filteredList.get(1).getFirstName());
        assertEquals("Mary", filteredList.get(2).getFirstName());

        String searchText = "ou";
        ArrayList<Instructor> filtered = accessInstructors.filterInstructor(searchText, instructors);
        assertEquals(1, filtered.size());
        assertEquals("Bouvier", filtered.get(0).getLastName());
    }

    // ReviewableItem interface testing
    @Test
    public void testGetItems() {
        List<ReviewableItem> items = accessInstructors.getItems();
        ReviewableItem item1 = new Instructor("Dr.", "Gary", "Chalmers");
        ReviewableItem expected = items.get(0);

        assertTrue("ensure first item present in DB",((Instructor)item1).equals((Instructor)expected));
    }
    @Test
    public void testFilter() {
        List <ReviewableItem> items = accessInstructors.getItems();

        String search = "Bouvier";
        List<ReviewableItem> filteredList = accessInstructors.filter(search, items);
        assertEquals(1,filteredList.size());
        assertEquals("Bouvier", ((Instructor)filteredList.get(0)).getLastName());

        String searchText = "lollllll";
        List<ReviewableItem> filtered = accessInstructors.filter(searchText, items);
        assertEquals(0,filtered.size());

        search = "Rob";
        List<ReviewableItem> list= accessInstructors.filter(search.toLowerCase(), items);
        assertEquals(1,list.size());
    }
}
