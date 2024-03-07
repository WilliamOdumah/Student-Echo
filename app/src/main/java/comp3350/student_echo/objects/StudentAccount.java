package comp3350.student_echo.objects;

import java.io.Serializable;

public class StudentAccount implements Serializable {
    private String username;
    private String password;
    private String email;


    public StudentAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getEmail() { return email;}

    public Boolean equals(StudentAccount sa){
        return (this.username.equals(sa.getUsername()) && this.password.equals(sa.getPassword()) &&this.email.equals(sa.getEmail()));
    }
}
