package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.Exceptions.InvalidAccountException;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.business.AccountValidator;
import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.objects.StudentAccount;

public class SignUpActivity extends AppCompatActivity {

    private AccessAccounts accessAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sign_up);

        accessAccounts = new AccessAccounts();
    }


    public void buttonSignUpOnClick(View v){
        Intent signUpIntent = new Intent(SignUpActivity.this, HomeActivity.class);

        EditText inputEmail = findViewById(R.id.inputSignUpEmail);
        EditText inputUsername = findViewById(R.id.inputSignUpUsername);
        EditText inputPassword = findViewById(R.id.inputSignUpPassword);
        EditText inputConfirmPassword = findViewById(R.id.inputSignUpConfirmPassword);


        String email = inputEmail.getText().toString();
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        try {
            StudentAccount toAdd = new StudentAccount(username, password, confirmPassword, email);
            accessAccounts.addAccount(toAdd);
        } catch (InvalidAccountException e) {
            // show why invalid
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        LoginManager.performLogin(username, password);
        Toast.makeText(this, "Successfully created account!", Toast.LENGTH_LONG).show();
        SignUpActivity.this.startActivity(signUpIntent);
        finish();
    }
}
