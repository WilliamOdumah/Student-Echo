package comp3350.student_echo.objects;

import java.io.Serializable;

public class StudentAccount implements Serializable {
    private String username;
    private String password;
    private String confirmedPassword;
    private String email;


    public StudentAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.confirmedPassword = "";
    }
    public StudentAccount(String username, String password, String confirmedPassword, String email) {
        this(username, password, email);
        this.confirmedPassword = confirmedPassword;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getConfirmedPassword() {return confirmedPassword;}
    public String getEmail() { return email;}

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public Boolean equals(StudentAccount sa){
        return (this.email.equals(sa.getEmail()));
    }
}
