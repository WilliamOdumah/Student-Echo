package comp3350.student_echo.business;
import comp3350.student_echo.objects.StudentAccount;

public class AuthenticateLogin {

    private AccessAccounts accountsData;
public AuthenticateLogin(){
    accountsData=new AccessAccounts();
}

public  StudentAccount findAccount(StudentAccount dummyAccount){


    return accountsData.findLoginMatch(dummyAccount);
}

}
