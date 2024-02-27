package comp3350.student_echo.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.business.StudentAccountManager;
import comp3350.student_echo.objects.StudentAccount;

public class LoginManagerTest {
    StudentAccountManager sam = new StudentAccountManager();

    @Test
    public void testLogin() {
        StudentAccount sa = sam.createAccount("yo@myumanitoba.ca", "hello", "pass", "pass");
        assertTrue("Login with correct credentials", LoginManager.performLogin("hello", "pass"));
        assertFalse("wrong password", LoginManager.performLogin("hello", "wrongpass"));
        assertFalse("wrong username", LoginManager.performLogin("nothere", "pass"));
    }

    @Test
    public void testGetLoggedInUser() {
        StudentAccount sa = sam.createAccount("email@myumanitoba.ca", "ye", "letmein", "letmein");
        assertNull("get null when no one logged in", LoginManager.getLoggedInUser());

        LoginManager.performLogin("ye", "letmein");
        assertEquals("get logged in user", sa, LoginManager.getLoggedInUser());
        assertNotEquals("dont get another user", null, LoginManager.getLoggedInUser());
    }

    @Test
    public void testLogout() {
        LoginManager.performLogin("hello", "pass");
        LoginManager.performLogout();
        assertNull("logout should result in no more user", LoginManager.getLoggedInUser());
    }
}