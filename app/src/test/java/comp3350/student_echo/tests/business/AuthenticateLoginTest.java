package comp3350.student_echo.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.student_echo.business.AuthenticateLogin;
import comp3350.student_echo.business.StudentAccountManager;
import comp3350.student_echo.objects.StudentAccount;

public class AuthenticateLoginTest {
    @Test
    public void testValidate() {
        StudentAccountManager sam = new StudentAccountManager();
        StudentAccount a1 = sam.createAccount("yo@myumanitoba.ca", "hello", "pass", "pass");

        assertEquals("existing account, correct password", a1, AuthenticateLogin.validate(a1.getUsername(), a1.getPassword()));
        assertNull("existing account, incorrect password", AuthenticateLogin.validate(a1.getUsername(), "wrongPass"));
        assertNull("non-existing account", AuthenticateLogin.validate("randomUsername", "pass"));
        assertNull("null username and password", AuthenticateLogin.validate(null, null));
    }
}