package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.Exceptions.InvalidAccountException;
import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.business.AccountValidator;
import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;

public class UserActivity extends AppCompatActivity {

    private AccessAccounts accessAccounts;
    private EditText inputUsername;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_user);

        accessAccounts = new AccessAccounts();

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

        try {
            StudentAccount toAdd = new StudentAccount(username, password, email);
            accessAccounts.addAccount(toAdd);
        } catch (InvalidAccountException e) {
            // show why invalid
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        LoginManager.performLogout();
        LoginManager.performLogin(username,password);
        Toast.makeText(this, "Account has been updated!", Toast.LENGTH_SHORT).show();
        UserActivity.this.startActivity(saveChangesIntent);
    }

    public void buttonLogout(View v) {
        LoginManager.performLogout();
        Intent logoutIntent= new Intent(UserActivity.this, LoginActivity.class);
        UserActivity.this.startActivity(logoutIntent);
        finish();
    }

    public void buttonDeleteAccount(View v) {
        accessAccounts.deleteAccount(LoginManager.getLoggedInUser());
        LoginManager.performLogout();
        Intent deleteIntent = new Intent(UserActivity.this, LoginActivity.class);
        UserActivity.this.startActivity(deleteIntent);
        finish();
    }
}