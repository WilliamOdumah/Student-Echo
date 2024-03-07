package comp3350.student_echo.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessDepartments;
import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.business.access.AccessCourses;
import comp3350.student_echo.objects.Department;
import comp3350.student_echo.objects.reviewableItems.Course;

public class AddCourseActivity extends AppCompatActivity {
    private AccessCourses accessCourses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Spinner dropDown=findViewById(R.id.spinner2);
        accessCourses = new AccessCourses();
        AccessDepartments data= new AccessDepartments();
        List<Department> departments = data.getDepartmentList();

        ArrayList<String> departmentsName= new ArrayList<>();

        for (int i = 0; i< departments.size(); i++){
            departmentsName.add(departments.get(i).getDepartmentName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departmentsName);
        dropDown.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    public void buttonCreateNewCourse(View view) {

        // get info from user
        EditText ID = (EditText) findViewById(R.id.courseID);
        EditText name = (EditText) findViewById(R.id.courseName);
        String courseID = ID.getText().toString().toUpperCase();
        String courseName = name.getText().toString();
        String department = ((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString();

        // try adding course
        try {
            Course toAdd = new Course(department, courseID, courseName);
            accessCourses.addCourse(toAdd);
            // return to previous page
            finish();
        } catch(InvalidCourseException e) {
            // show why invalid
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                LoginManager.performLogout();
                Intent logoutIntent= new Intent(AddCourseActivity.this, LoginActivity.class);
                AddCourseActivity.this.startActivity(logoutIntent);
                return true;
            case R.id.accountSettings:
                Intent newIntent= new Intent(AddCourseActivity.this, UserActivity.class);
                AddCourseActivity.this.startActivity(newIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}