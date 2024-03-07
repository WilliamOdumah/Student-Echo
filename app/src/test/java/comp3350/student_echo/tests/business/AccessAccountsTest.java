package comp3350.student_echo.tests.business;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.AccountPersistence;
import comp3350.student_echo.persistence.hsqldb.AccountPersistenceHSQLDB;
import comp3350.student_echo.persistence.stubs.AccountPersistenceStub;

public class AccessAccountsTest {

    private AccessAccounts accessAccounts;
    @Before
    public void setUp() {
        AccountPersistence stub = new AccountPersistenceStub();

        accessAccounts = new AccessAccounts(stub);

    }

    @Test
    public void getAccountsTest() {

        List<StudentAccount> accounts = accessAccounts.getAccounts();

        for(int i=0;i<accounts.size();i++) {
            String username = accounts.get(i).getUsername();
            assertNotNull("testing if " +  username + "'s account exists", accessAccounts.getAccount(username));
        }


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