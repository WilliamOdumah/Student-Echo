package comp3350.student_echo.business;
import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.CoursePersistence;
import java.util.List;
public class AuthenticateLogin {

    private AccessAccounts accountsData;
public AuthenticateLogin(){
    accountsData=new AccessAccounts();
}

public  StudentAccount findAccount(StudentAccount dummyAccount){


    return accountsData.findLoginMatch(dummyAccount);
}

}
