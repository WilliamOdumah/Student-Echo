package comp3350.student_echo.tests.business;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import comp3350.student_echo.business.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;

public class AccessAccountsTest {

    private final AccessAccounts accessAccounts;

    public AccessAccountsTest() {
        accessAccounts = new AccessAccounts();
    }

    @Test
    // test addAccount
    public void addAccountsTest() {
        // note that we don't test if an account is valid in AccessAccounts
        // as StudentAccountManager is doing all of this for us
        // this allows for a highly cohesive architecture! (yay)

        StudentAccount newAccount = new StudentAccount("c", "c", "c@myumanitoba.ca");

        accessAccounts.addAccount(newAccount);
        assertNotNull("testing with newly-added account", accessAccounts.getAccount("c"));
    }

    @Test
    // test getAccount
    public void getAccountTest() {
        StudentAccount newAccount = new StudentAccount("a", "a", "a@myumanitoba.ca");

        assertNotNull("testing with pre-existing account", accessAccounts.getAccount("kelly"));

        accessAccounts.addAccount(newAccount);
        assertNotNull("testing with newly-added account", accessAccounts.getAccount("a"));

        assertNull("testing with non-existent account", accessAccounts.getAccount("b"));
    }
}