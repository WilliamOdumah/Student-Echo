package comp3350.student_echo.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AuthenticateLogin;
import comp3350.student_echo.objects.StudentAccount;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Activity_Login extends AppCompatActivity {

    private StudentAccount LoggedInAccount;




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

        Intent loginIntent = new Intent(Activity_Login.this, HomeActivity.class);

        EditText Username=(EditText)findViewById(R.id.TextUsername);
        EditText Password=(EditText)findViewById(R.id.TextPassword);;
        String username=Username.getText().toString().trim();
        String password=Password.getText().toString().trim();

        AuthenticateLogin newAttempt=new AuthenticateLogin();
        StudentAccount dummyAccount= new StudentAccount(username,password,"dummy");
        StudentAccount returned=newAttempt.findAccount(dummyAccount);

        if(returned!=null) {
            loginIntent.putExtra("LoggedAccount",returned);
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