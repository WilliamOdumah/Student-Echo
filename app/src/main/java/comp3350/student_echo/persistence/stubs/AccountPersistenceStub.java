package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.AccountPersistence;

public class AccountPersistenceStub implements AccountPersistence {

    private final List<StudentAccount> accounts;

    public AccountPersistenceStub(){
        accounts = new ArrayList<>();
        accounts.add(new StudentAccount("william","odumah","william@myumanitoba.ca"));
        accounts.add(new StudentAccount("kelly","villamayor","kelly@myumanitoba.ca"));
        accounts.add(new StudentAccount("rishamdeep","singh","risham@myumanitoba.ca"));
        accounts.add(new StudentAccount("ning","liu","ning@myumanitoba.ca"));
        accounts.add(new StudentAccount("brett","aseltine","brett@myumanitoba.ca"));
        accounts.add(new StudentAccount("1", "2", "12@myumanitoba.ca"));
    }

    @Override
    public void addAccount(StudentAccount toAdd){
        accounts.add(toAdd);
    }

    // TODO
    @Override
    public void updateAccount(StudentAccount currentStudent) {

    }

    public List<StudentAccount> getAccountSequential() {
        return Collections.unmodifiableList(accounts);
    }
}
