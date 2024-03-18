package comp3350.student_echo.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.business.AccountValidator;
import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;

public class LoginManagerTest {

    private AccessAccounts accounts;
    private AccountValidator sam;
    @Before
    public void setUp(){
        Services.useStub();
        accounts=new AccessAccounts();
        sam=new AccountValidator();
    }
    @Test
    public void testLogin() {
        StudentAccount sa =sam.createAccount("yo@myumanitoba.ca", "hello", "pass", "pass");

        assertTrue("Login with correct credentials", LoginManager.performLogin("hello", "pass"));
        assertFalse("wrong password", LoginManager.performLogin("hello", "wrongpass"));
        assertFalse("wrong username", LoginManager.performLogin("nothere", "pass"));
        LoginManager.performLogout();
        accounts.deleteAccount(sa);
    }

    @Test
    public void testGetLoggedInUser() {
        StudentAccount sa =sam.createAccount("yoNew@myumanitoba.ca", "newUser", "pass", "pass");
        assertNull("get null when no one logged in", LoginManager.getLoggedInUser());

        LoginManager.performLogin("newUser", "pass");
        String user=LoginManager.getLoggedInUser().getUsername();
        String userSa=sa.getUsername();
        assert( userSa.equals(user));
        LoginManager.performLogout();

        assertEquals("dont get another user", null, LoginManager.getLoggedInUser());
        accounts.deleteAccount(sa);
    }

    @Test
    public void testLogout() {

        StudentAccount sa =sam.createAccount("yo@myumanitoba.ca", "hello", "pass", "pass");

        LoginManager.performLogin("hello", "pass");
        LoginManager.performLogout();
        assertNull("logout should result in no more user", LoginManager.getLoggedInUser());
        accounts.deleteAccount(sa);
    }
}