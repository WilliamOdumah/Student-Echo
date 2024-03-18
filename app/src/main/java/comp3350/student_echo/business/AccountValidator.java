package comp3350.student_echo.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.student_echo.business.Exceptions.InvalidAccountException;
import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.AccountPersistence;

public class AccountValidator {

    public static void validateAccount(StudentAccount sa, AccountPersistence p) throws InvalidAccountException {
        AccessAccounts accessAccounts = new AccessAccounts(p);
        // valid email
        if( !validEmail(sa.getEmail()) ) {
            throw new InvalidAccountException("email must end with @myumanitoba.ca");
        } else if( emailExists(sa.getEmail(), accessAccounts) ) {
            throw new InvalidAccountException("account already exists with given email.");
        } else if( !validUsername(sa.getUsername()) ) {
            throw new InvalidAccountException("username cannot be empty");
        } else if( !validPassword(sa.getPassword(), sa.getConfirmedPassword()) )  {
            throw new InvalidAccountException("passwords cannot be empty and must match");
        }
    }

    public static boolean validEmail(String email) {
        // the pattern every valid uofm student email should follow
        String regexPattern = "^[a-zA-Z0-9]+@myumanitoba\\.ca$";

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches(); // true if email address matches the established pattern
    }

    public static boolean emailExists(String email, AccessAccounts access) {
        return access.getAccountUsingEmail(email) != null;
    }

    public static boolean validPassword(String password, String confirmPass) {
        return password != null && !password.equals("") && password.equals(confirmPass);
    }

    public static boolean validUsername(String username) {
        return username != null && !username.equals("");
    }
}

