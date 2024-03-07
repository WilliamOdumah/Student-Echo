package comp3350.student_echo.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;

public class StudentAccountManager {
    private final AccessAccounts accountsData;

    public StudentAccountManager() {
        accountsData=new AccessAccounts();
    }

    // creates account and adds to persistence
    public StudentAccount createAccount(String email, String username, String password, String confirmPass) {

        if (verifyEmail(email) && verifyUniqueness(email) && verifyPassword(password, confirmPass) && verifyUsername(username)) {
            StudentAccount account = new StudentAccount(username, password, email);
            accountsData.addAccount(account);
            return account;
        }
        return null;
    }

    public StudentAccount createUpdatedAccount(String email, String username, String password) {
        if (verifyPassword(password, password) && verifyUsername(username)) {
            StudentAccount account = new StudentAccount(username, password, email);
            accountsData.updateAccount(account);
            return account;
        }
        return null;
    }

    public boolean verifyEmail(String email) {
        // the pattern every valid uofm student email should follow
        String regexPattern = "^[a-zA-Z0-9]+@myumanitoba\\.ca$";

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches(); // true if email address matches the established pattern
    }

    // there should be no account with the same email
    public boolean verifyUniqueness(String email) {
        return accountsData.getAccountUsingEmail(email) == null;
    }

    public boolean verifyPassword(String password, String confirmPass) {
        return password != null && !password.equals("") && password.equals(confirmPass);
    }

    public boolean verifyUsername(String username) {
        return username != null && !username.equals("");
    }

    public boolean deleteAccount(StudentAccount studentAccount) {
        return accountsData.deleteAccount(studentAccount);
    }
}

