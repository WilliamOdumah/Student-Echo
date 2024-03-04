package comp3350.student_echo.objects;

public class StudentAccount {
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
}
