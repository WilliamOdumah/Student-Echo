package comp3350.student_echo.tests.business.integration;

import static org.junit.Assert.assertNotNull;

import android.accounts.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.business.access.AccessCourses;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.CoursePersistence;
import comp3350.student_echo.persistence.hsqldb.AccountPersistenceHSQLDB;
import comp3350.student_echo.persistence.hsqldb.CoursePersistenceHSQLDB;
import comp3350.student_echo.tests.utils.TestUtils;

public class AccessAccountsIT {

    private AccessAccounts accounts;
    private File tempDB;

    private StudentAccount toAdd;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final AccountPersistenceHSQLDB persistence = new AccountPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));

        // inject real HSQLDB
        this.accounts= new AccessAccounts(persistence);

        toAdd= new StudentAccount("abc","abc","abc@myumanitoba.ca");
    }


    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

    @Test
    public void testGetAccounts(){

        List<StudentAccount> accountsList = accounts.getAccounts();

        for(int i=0;i<accountsList.size();i++) {
            String username = accountsList.get(i).getUsername();
            assertNotNull("testing if " +  username + "'s account exists", accounts.getAccount(username));
        }

    }

    @Test
    public void testAddAccounts(){
        accounts.addAccount(toAdd);
        assertNotNull(accounts.getAccount(toAdd.getUsername()));
        accounts.deleteAccount(toAdd);
    }


    @Test
    public void testDeleteAccounts(){
        accounts.addAccount(toAdd);
        accounts.deleteAccount(toAdd);
        assert(accounts.getAccount(toAdd.getUsername())==null);
    }

    @Test
    public void testUpdateAccount(){
        accounts.addAccount(toAdd);
        toAdd= new StudentAccount("abcd","abc123","abc@myumanitoba.ca");
        accounts.updateAccount(toAdd);
        assertNotNull(accounts.getAccount(toAdd.getUsername()));
    }





}
