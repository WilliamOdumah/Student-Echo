package comp3350.student_echo.business;

import java.util.List;

import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;

public class LoginManager {
    private static StudentAccount loggedInUser;

    public static StudentAccount getLoggedInUser() {return loggedInUser;}

    public static boolean performLogin(String username, String password) {
        loggedInUser = getAccountWithCredentials(username, password);
        return loggedInUser != null;
    }
    public static void performLogout() {
        loggedInUser = null;
    }
    private static StudentAccount getAccountWithCredentials(String username, String password) {
        AccessAccounts ac = new AccessAccounts();
        List<StudentAccount> accounts = ac.getAccounts();
        for(StudentAccount cur : accounts) {
            if(cur.getUsername().equals(username) && cur.getPassword().equals(password)) {
                return cur;
            }
        }
        return null;
    }


}
