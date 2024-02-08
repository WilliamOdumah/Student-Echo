package comp3350.student_echo.business;

import java.util.List;

import comp3350.student_echo.objects.StudentAccount;

public class AuthenticateLogin {
    public static StudentAccount validate(String username, String password) {
        AccessAccounts ac = new AccessAccounts();
        List<StudentAccount> accounts = ac.getAccounts();
        for(StudentAccount cur : accounts) {
            if(cur.getUsername().equals(username) && cur.getPassword().equals(password)){
                return cur;
            }
        }
        return null;
    }
}
