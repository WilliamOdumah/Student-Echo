package comp3350.student_echo.tests.business;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.student_echo.business.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;

public class AccessAccountsTest {

    private final AccessAccounts accessAccounts;

    public AccessAccountsTest() {
        accessAccounts = new AccessAccounts();

        findLogInMatchTest();
        getAccountTest();
    }

    @Test
    public void findLogInMatchTest() {

        StudentAccount test1Account = new StudentAccount("kelly", "villamayor", "villamak@myumanitoba.ca");
        StudentAccount test2Account = new StudentAccount("a", "a", "a@myumanitoba.ca");
        StudentAccount test3Account = new StudentAccount("b", "b", "b@myumanitoba.ca");

        accessAccounts.addAccount(test2Account);

        assertNotNull("finding a log-in match for a pre-test existing account", accessAccounts.findLoginMatch(test1Account));
        assertNotNull("finding a log-in match for a newly-added account", accessAccounts.findLoginMatch(test2Account));
        assertNull("returning null when trying to find a non-existing account", accessAccounts.findLoginMatch(test3Account));
    }

    @Test
    public void getAccountTest() {

        StudentAccount test1Account = new StudentAccount("kelly", "villamayor", "villamak@myumanitoba.ca");
        StudentAccount test2Account = new StudentAccount("a", "a", "a@myumanitoba.ca"); // already added by previous test
        StudentAccount test3Account = new StudentAccount("b", "b", "b@myumanitoba.ca");

        assertNotNull("getting a pre-test existing account", accessAccounts.findLoginMatch(test1Account));
        assertNotNull("getting a newly-added account", accessAccounts.findLoginMatch(test2Account));
        assertNull("returning a null when trying to get a non-existing account", accessAccounts.findLoginMatch(test3Account));
    }
}