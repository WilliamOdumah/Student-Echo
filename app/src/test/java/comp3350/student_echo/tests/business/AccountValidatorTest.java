package comp3350.student_echo.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.AccountValidator;
import comp3350.student_echo.objects.StudentAccount;

public class AccountValidatorTest {
    AccountValidator sam;
    @Before
    public void before() {

        Services.useStub();
        sam = new AccountValidator();
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
        assertTrue("ending with @myumanitoba.ca email is valid", sam.validEmail("abcdef@myumanitoba.ca"));
        assertFalse("not ending with @umanitoba.ca is not valid", sam.validEmail("afcdef@ualberta.ca"));
        assertFalse("need full @umanitoba", sam.validEmail("hello@uman.ca"));
        assertFalse("Need prefix to @myumanitoba.ca", sam.validEmail("@myumanitoba.ca"));
        assertFalse("need @ portion", sam.validEmail("testmyumanitoba.ca"));
    }
    @Test
    public void testVerifyUsername() {
        // add hello as username already in DB
        sam.createAccount("email@myumanitoba.ca", "hello", "pass", "pass");


        assertTrue("a new username can be created", sam.validUsername("newUsername"));
        assertFalse("Reject an empty username", sam.validUsername(""));
        assertFalse("Reject an null username", sam.validUsername(null));
    }

    @Test
    public void testVerifyUniqueness()
    {
        StudentAccount sa=sam.createAccount("newemail@myumanitoba.ca", "newMan", "pass", "pass");

        assertFalse("reject same email", sam.uniqueEmail("newemail@myumanitoba.ca"));
        sam.deleteAccount(sa);
    }

    @Test
    public void testVerifyPassword() {
        assertTrue("accept equal passwords", sam.validPassword("password", "password"));
        assertFalse("Reject different word", sam.validPassword("hello", "there"));
        assertFalse("Reject different case", sam.validPassword("PASSWORD", "password"));
        assertFalse("Reject different spacing around", sam.validPassword(" yo ", "yo"));
        assertFalse("Reject empty password", sam.validPassword("", ""));
        assertFalse("Reject a null password", sam.validPassword(null, null));
    }
}