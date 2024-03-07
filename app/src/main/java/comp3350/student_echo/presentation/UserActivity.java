package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.business.StudentAccountManager;
import comp3350.student_echo.objects.StudentAccount;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void buttonSaveChanges(View v) {
        Intent saveChangesIntent = new Intent(UserActivity.this, HomeActivity.class);

        EditText inputUsername = findViewById(R.id.inputUserUsername);
        EditText inputPassword = findViewById(R.id.inputUserPassword);

        String email = LoginManager.getLoggedInUser().getEmail();
        String username =  inputUsername.getText().toString();
        String password =  inputPassword.getText().toString();

        StudentAccountManager studentAccountManager = new StudentAccountManager();
        StudentAccount studentAccount = studentAccountManager.createUpdatedAccount(email, username, password);

        if(studentAccount != null) {
            System.out.println("Successfully created your account!");
            System.out.println("Successfully saved your changes!");
            UserActivity.this.startActivity(saveChangesIntent);
        } else {
            Toast.makeText(this, "Uh oh! Looks something went wrong with saving your changes. Please try again!",Toast.LENGTH_LONG).show();
        }
    }

    public void buttonLogout(View v) {
        LoginManager.performLogout();
        Intent logoutIntent= new Intent(UserActivity.this, LoginActivity.class);
        UserActivity.this.startActivity(logoutIntent);
        finish();
    }

    public void buttonDeleteAccount(View v) {
        StudentAccount currentUser = LoginManager.getLoggedInUser();

        StudentAccountManager studentAccountManager = new StudentAccountManager();
        studentAccountManager.deleteAccount(currentUser);

        LoginManager.performLogout();
        Intent deleteIntent = new Intent(UserActivity.this, LoginActivity.class);
        UserActivity.this.startActivity(deleteIntent);
        finish();
    }
}