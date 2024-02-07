package comp3350.student_echo.objects;

import java.io.Serializable;

public class StudentAccount implements Serializable {
    private String username;
    private String password;
    private String email;
    private String studentNumber;

    public StudentAccount(String username, String password, String email, String studentNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.studentNumber = studentNumber;
    }

    public boolean loginAuthentication(StudentAccount toCompare){

        return toCompare.username.equals(this.username)&&toCompare.password.equals(this.password);

    }
}
