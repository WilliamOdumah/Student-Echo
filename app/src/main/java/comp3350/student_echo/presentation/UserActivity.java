package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.business.StudentAccountManager;
import comp3350.student_echo.objects.StudentAccount;

public class UserActivity extends AppCompatActivity {


    private EditText inputUsername;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_user);

        inputUsername = findViewById(R.id.inputUserUsername);
        inputPassword = findViewById(R.id.inputUserPassword);

        inputUsername.setText(LoginManager.getLoggedInUser().getUsername());
        inputPassword.setText((String) (LoginManager.getLoggedInUser().getPassword()));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    public void buttonSaveChanges(View v) {
        Intent saveChangesIntent = new Intent(UserActivity.this, HomeActivity.class);


        String email = LoginManager.getLoggedInUser().getEmail();
        String username =  inputUsername.getText().toString();
        String password =  inputPassword.getText().toString();

        StudentAccountManager studentAccountManager = new StudentAccountManager();
        StudentAccount studentAccount = studentAccountManager.createUpdatedAccount(email, username, password);

        if(studentAccount != null) {
            System.out.println("Successfully created your account!");
            System.out.println("Successfully saved your changes!");
            LoginManager.performLogout();
            LoginManager.performLogin(username,password);
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
        StudentAccountManager studentAccountManager = new StudentAccountManager();
        studentAccountManager.deleteAccount(LoginManager.getLoggedInUser());
        LoginManager.performLogout();
        Intent deleteIntent = new Intent(UserActivity.this, LoginActivity.class);
        UserActivity.this.startActivity(deleteIntent);
        finish();
    }
}