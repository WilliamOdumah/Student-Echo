package comp3350.student_echo.tests.objects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.objects.StudentAccount;

public class StudentAccountTest {
    private StudentAccount a1;

    @Before
    public void before() {
        a1 = new StudentAccount("username", "password", "email@email.com");
    }

    @Test
    public void testStudentAccountCreation() {
        assertEquals("Check username", a1.getUsername(), "username");
        assertNotEquals("Check username", a1.getUsername(), "notusername");

        assertEquals("Check password", a1.getPassword(), "password");
        assertNotEquals("Check password", a1.getPassword(), "notpassword");

        assertEquals("Check email", a1.getEmail(), "email@email.com");
        assertNotEquals("Check email", a1.getEmail(), "notemail@email.com");
    }
}