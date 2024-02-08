package comp3350.student_echo.objects;

import java.io.Serializable;

public class StudentAccount implements Serializable {
    private String username;
    private String password;
    private String email; // this will be used for further account creation validation


    public StudentAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }

    public String getEmail() { return email;}
}
