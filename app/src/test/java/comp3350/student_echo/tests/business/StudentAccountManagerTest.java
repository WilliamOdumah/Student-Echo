package comp3350.student_echo.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.StudentAccountManager;
import comp3350.student_echo.objects.StudentAccount;

public class StudentAccountManagerTest {
    StudentAccountManager sam;
    @Before
    public void before() {

        Services.useStub();
        sam = new StudentAccountManager();
    }

    @After
    public void tear(){
        Services.useHsql();
    }

    @Test
    public void testCreateAccount() {
        assertNotNull( "Create valid account", sam.createAccount("hello@myumanitoba.ca", "username", "password", "password"));
        sam.deleteAccount(new StudentAccount("username", "password","hello@myumanitoba.ca"));
        assertNull("Reject with invalid email", sam.createAccount("hello@gmail.com", "username", "password", "password"));
        assertNull("Reject with invalid username", sam.createAccount("hello@myumanitoba.ca", "", "password", "password"));
        assertNull("Reject with invalid password", sam.createAccount("hello@myumanitoba.ca", "username", "pass", "diff"));
    }

    @Test
    public void testVerifyEmail() {
        assertTrue("ending with @myumanitoba.ca email is valid", sam.verifyEmail("abcdef@myumanitoba.ca"));
        assertFalse("not ending with @umanitoba.ca is not valid", sam.verifyEmail("afcdef@ualberta.ca"));
        assertFalse("need full @umanitoba", sam.verifyEmail("hello@uman.ca"));
        assertFalse("Need prefix to @myumanitoba.ca", sam.verifyEmail("@myumanitoba.ca"));
        assertFalse("need @ portion", sam.verifyEmail("testmyumanitoba.ca"));
    }
    @Test
    public void testVerifyUsername() {
        // add hello as username already in DB
        sam.createAccount("email@myumanitoba.ca", "hello", "pass", "pass");


        assertTrue("a new username can be created", sam.verifyUsername("newUsername"));
        assertFalse("Reject an empty username", sam.verifyUsername(""));
        assertFalse("Reject an null username", sam.verifyUsername(null));
    }

    @Test
    public void testVerifyUniqueness()
    {
        StudentAccount sa=sam.createAccount("newemail@myumanitoba.ca", "newMan", "pass", "pass");

        assertFalse("reject same email", sam.verifyUniqueness("newemail@myumanitoba.ca"));
        sam.deleteAccount(sa);
    }

    @Test
    public void testVerifyPassword() {
        assertTrue("accept equal passwords", sam.verifyPassword("password", "password"));
        assertFalse("Reject different word", sam.verifyPassword("hello", "there"));
        assertFalse("Reject different case", sam.verifyPassword("PASSWORD", "password"));
        assertFalse("Reject different spacing around", sam.verifyPassword(" yo ", "yo"));
        assertFalse("Reject empty password", sam.verifyPassword("", ""));
        assertFalse("Reject a null password", sam.verifyPassword(null, null));
    }
}