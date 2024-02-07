package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.AccountPersistence;

public class AccountPersistenceStub implements AccountPersistence {

    private List<StudentAccount> accounts;


    public AccountPersistenceStub(){
        accounts=new ArrayList<>();
        accounts.add(new StudentAccount("william","odumah","william@myumanitoba.ca","1"));
        accounts.add(new StudentAccount("kelly","villamayor","kelly@myumanitoba.ca","2"));
        accounts.add(new StudentAccount("rishamdeep","singh","risham@myumanitoba.ca","3"));
        accounts.add(new StudentAccount("ning","liu","ning@myumanitoba.ca","4"));
        accounts.add(new StudentAccount("brett","aseltine","brett@myumanitoba.ca","5"));

    }





    public List<StudentAccount> getAccountSequential() {
        return Collections.unmodifiableList(accounts);
    }


    public StudentAccount insertAccount(StudentAccount currentStudent) {
        // don't bother checking for duplicates
        accounts.add(currentStudent);
        return currentStudent;
    }


    public StudentAccount updateAccount(StudentAccount currentInstructor) {
        int index;

        index = accounts.indexOf(currentInstructor);
        if (index >= 0)
        {
            accounts.set(index, currentInstructor);
        }
        return currentInstructor;
    }


    public void deleteAccount(StudentAccount currentAccount) {
        int index;

        index = accounts.indexOf(currentAccount);
        if (index >= 0)
        {
            accounts.remove(index);
        }
    }
}
