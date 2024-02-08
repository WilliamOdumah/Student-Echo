package comp3350.student_echo.business;

import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.AccountPersistence;


public class AccessAccounts {

    private final AccountPersistence accountPersistence;
    private final List<StudentAccount> accounts;

    public AccessAccounts()
    {
        accountPersistence = Services.getAccountPersistence();
        accounts=accountPersistence.getAccountSequential();

    }

    public List<StudentAccount> getAccounts() {
        return accounts;
    }

    public void addAccount(StudentAccount toAdd){
        accountPersistence.addAccount(toAdd);
    }

    public StudentAccount getAccount(String username){

        StudentAccount currentAccount;

        for (int i=0; i<accounts.size();i++){
            currentAccount=accounts.get(i);
            if(currentAccount.getUsername().equals(username))
                return currentAccount;
        }

        return null;
    }
}
