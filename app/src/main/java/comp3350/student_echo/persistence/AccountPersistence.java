package comp3350.student_echo.persistence;

import java.util.List;

import comp3350.student_echo.objects.StudentAccount;

public interface AccountPersistence {

    List<StudentAccount> getAccountSequential();

    void addAccount(final StudentAccount currentStudent);

    void updateAccount (final StudentAccount currentStudent);
}
