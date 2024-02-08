package comp3350.student_echo.tests.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.student_echo.objects.StudentAccount;

public class StudentAccountTest {
    StudentAccount a1, a2, a3;
    @Before
    public void before() {
        a1 = new StudentAccount("username", "password", "email@email.com");
        a2 = new StudentAccount("username", "password", "email@email.com");
        a3 = new StudentAccount("hello", "there", "yo@lol.com");
    }

    @Test
    public void testStudentAccountCreation() {
        //TODO
        assertEquals("Check username", a1.getUsername(), "username");
        assertNotEquals("Check username", a1.getUsername(), "notusername");
    }
}