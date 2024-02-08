package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessAccounts;
import comp3350.student_echo.business.AuthenticateLogin;
import comp3350.student_echo.objects.StudentAccount;


public class Activity_Login extends AppCompatActivity {

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

        // ensure username and password valid
        StudentAccount account = AuthenticateLogin.validate(username, password);

        if(account != null) {
            Intent loginIntent = new Intent(Activity_Login.this, HomeActivity.class);
            loginIntent.putExtra("LoggedAccount", account);
            Activity_Login.this.startActivity(loginIntent);
        }
        else {
            Toast.makeText(this, "Check your username and password!",Toast.LENGTH_LONG).show();
        }
    }

    public void buttonSignupOnClick(View v){
        Intent signUpIntent= new Intent(Activity_Login.this, SignUpActivity.class);
        Activity_Login.this.startActivity(signUpIntent);
    }
}