package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.LoginManager;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                LoginManager.performLogout();
                Intent logoutIntent= new Intent(HomeActivity.this, LoginActivity.class);
                HomeActivity.this.startActivity(logoutIntent);
                return true;
            case R.id.accountSettings:
                Intent newIntent= new Intent(HomeActivity.this, UserActivity.class);
                HomeActivity.this.startActivity(newIntent);
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        LoginManager.performLogout();
        Intent logoutIntent= new Intent(HomeActivity.this, LoginActivity.class);
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_SINGLE_TOP);
        HomeActivity.this.startActivity(logoutIntent);
        finish();
    }
    public void buttonCourseOnClick(View v) {
        Intent courseIntent = new Intent(HomeActivity.this, ItemActivity.class);
        courseIntent.putExtra("Type", "Course");
        HomeActivity.this.startActivity(courseIntent);
    }
    public void buttonInstructorOnClick(View v) {
        Intent instructorIntent = new Intent(HomeActivity.this, ItemActivity.class);
        instructorIntent.putExtra("Type", "Instructor");
        HomeActivity.this.startActivity(instructorIntent);
    }
}
