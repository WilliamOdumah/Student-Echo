package comp3350.student_echo.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.student_echo.objects.StudentAccount;

public class StudentAccountManager {


    private AccessAccounts accountsData;
    public StudentAccountManager() {
        accountsData=new AccessAccounts();
    }

    // creates account and adds to persistence
    public StudentAccount createAccount(String email, String username, String password, String confirmPass) {
        System.out.println(verifyEmail(email));
        System.out.println(verifyUsername(username));
        System.out.println(verifyPassword(password, confirmPass));

        if (verifyEmail(email) &&
                verifyUsername(username) &&
                verifyPassword(password, confirmPass)) {
            StudentAccount account = new StudentAccount(username, password, email);
            accountsData.addAccount(account);
            return account;
        }
        return null;
    }

    public boolean verifyEmail(String email) {
        // the pattern every valid uofm student email should follow
        String regexPattern = "^[a-zA-Z0-9]+@myumanitoba\\.ca$";

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches(); // returns if email address matches the established pattern
    }

    // there should be no account with the same username
    public boolean verifyUsername(String username) {
        return username != null && !username.equals("") && accountsData.getAccount(username) == null;
    }

    public boolean verifyPassword(String password, String confirmPass) {
        return password != null && !password.equals("") && password.equals(confirmPass);
    }
}

