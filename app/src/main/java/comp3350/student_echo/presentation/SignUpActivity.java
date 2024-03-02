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

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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

        StudentAccountManager studentAccountManager = new StudentAccountManager();
        StudentAccount studentAccount = studentAccountManager.createAccount(email, username, password, confirmPassword);

        if(studentAccount != null) {
            System.out.println("Successfully created your account!");
            boolean success = LoginManager.performLogin(username, password);
            SignUpActivity.this.startActivity(signUpIntent);
        } else {
            Toast.makeText(this, "Uh oh! Looks something went wrong with creating your account. Please try again!",Toast.LENGTH_LONG).show();
        }
    }
}
