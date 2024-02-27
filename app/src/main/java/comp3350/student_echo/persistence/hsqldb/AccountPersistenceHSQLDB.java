package comp3350.student_echo.persistence.hsqldb;

import java.util.List;

import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.AccountPersistence;

public class AccountPersistenceHSQLDB implements AccountPersistence {
    @Override
    public List<StudentAccount> getAccountSequential() {
        return null;
    }

    @Override
    public void addAccount(StudentAccount currentStudent) {

    }
}
