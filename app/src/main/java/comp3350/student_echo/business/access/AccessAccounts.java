package comp3350.student_echo.business.access;

import java.util.Collections;
import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.AccountPersistence;
import comp3350.student_echo.persistence.CoursePersistence;


public class AccessAccounts {

    private AccountPersistence accountPersistence;

    public AccessAccounts() {
        accountPersistence = Services.getAccountPersistence(true);
    }

    public AccessAccounts(final AccountPersistence persistence) {
        accountPersistence = persistence;
    }

    public void addAccount(StudentAccount toAdd){
        accountPersistence.addAccount(toAdd);
    }

    public List<StudentAccount> getAccounts() {
        return Collections.unmodifiableList(accountPersistence.getAccountSequential());
    }

    public StudentAccount getAccount(String username) {
        List<StudentAccount> accounts = accountPersistence.getAccountSequential();
        StudentAccount currentAccount;

        for (int i=0; i<accounts.size();i++){
            currentAccount=accounts.get(i);
            if(currentAccount.getUsername().equals(username))
                return currentAccount;
        }

        return null;
    }

    public boolean updateAccount(StudentAccount studentAccount) {
        return accountPersistence.updateAccount(studentAccount);
    }

    public boolean deleteAccount(StudentAccount studentAccount) {
        return accountPersistence.deleteAccount(studentAccount);
    }
}
