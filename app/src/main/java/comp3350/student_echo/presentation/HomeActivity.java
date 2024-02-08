package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.objects.StudentAccount;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            super.onBackPressed();
            return true;
        }

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

    public void buttonLogOutOnClick(View v) {
        Intent logoutIntent= new Intent(HomeActivity.this, Activity_Login.class);
        HomeActivity.this.startActivity(logoutIntent);
    }
}
