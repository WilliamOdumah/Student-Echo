package comp3350.student_echo.presentation;

import comp3350.student_echo.R;
import comp3350.student_echo.objects.StudentAccount;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    private  StudentAccount loggedInAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        loggedInAccount= (StudentAccount)intent.getExtras().getSerializable("LoggedAccount");

        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void buttonCourseOnClick(View v) {
        Intent studentsIntent = new Intent(HomeActivity.this, CoursesActivity.class);

        studentsIntent.putExtra("LoggedAccount",loggedInAccount);

        HomeActivity.this.startActivity(studentsIntent);
    }

    public void buttonInstructorOnClick(View v) {
        Intent instructorIntent = new Intent(HomeActivity.this, InstructorActivity.class);
        instructorIntent.putExtra("LoggedAccount",loggedInAccount);
        HomeActivity.this.startActivity(instructorIntent);
    }

    public void buttonLogOutOnClick(View v){
        Intent logoutIntent= new Intent(HomeActivity.this, Activity_Login.class);
        HomeActivity.this.startActivity(logoutIntent);
    }
}
