package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.StudentAccount;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void buttonSubmitOnClick(View v){

        // get login info
        EditText Username=(EditText)findViewById(R.id.TextUsername);
        EditText Password=(EditText)findViewById(R.id.TextPassword);;
        String username=Username.getText().toString().trim();
        String password=Password.getText().toString().trim();

        // try login
        boolean success = LoginManager.performLogin(username, password);

        // go to home page if successful
        if(success) {
            Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
            LoginActivity.this.startActivity(loginIntent);
        } else {
            Toast.makeText(this, "Check your username and password!",Toast.LENGTH_LONG).show();
        }
    }

    public void buttonSignupOnClick(View v){
        Intent signUpIntent= new Intent(LoginActivity.this, SignUpActivity.class);
        LoginActivity.this.startActivity(signUpIntent);
    }
}