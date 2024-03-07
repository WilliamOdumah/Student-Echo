package comp3350.student_echo.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.objects.Department;
public class DepartmentTest {

    Department d1, d2,d3;
    @Before
    public void before() {
        d1 = new Department("CS");
        d2 = new Department("ACC");
        d3 = new Department("MATH");
    }

    @Test
    public void testDepartmentCreation() {
        assertEquals("Check department name" , d1.getDepartmentName(), "CS");
        assertEquals("Check department name" , d2.getDepartmentName(), "ACC");
        assertEquals("Check department name" , d3.getDepartmentName(), "MATH");
    }

    @Test
    public void testEquals() {
        assertFalse("Different department", d1.equals(d3));
    }



}
