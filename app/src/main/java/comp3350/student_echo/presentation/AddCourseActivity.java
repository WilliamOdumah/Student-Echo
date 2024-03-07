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
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AddCourseManager;
import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.AccessDepartments;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.Department;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.persistence.hsqldb.DepartmentPersistenceHSQLDB;


public class AddCourseActivity extends AppCompatActivity {

    private String courseID;
    private String courseName;

    private String department;

    private Course newCourse;


    private List <Department> departments;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Spinner dropDown=findViewById(R.id.spinner2);
        AccessDepartments data= new AccessDepartments();
        departments=data.getDepartmentList();

        ArrayList<String> departmentsName= new ArrayList<>();

        for (int i=0; i<departments.size();i++){
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


        EditText ID = (EditText) findViewById(R.id.courseID);
        EditText name = (EditText) findViewById(R.id.courseName);


        courseID = ID.getText().toString().toUpperCase();
        courseName = name.getText().toString();
        department = ((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString();

        AddCourseManager addCourseManager = new AddCourseManager();
        newCourse = addCourseManager.createCourse(courseID, courseName, department);

        if (newCourse != null) {
            System.out.println("Successfully created the course!");
            Intent addCourse = new Intent(AddCourseActivity.this, ItemActivity.class);
            addCourse.putExtra("Type", "Course");
            AddCourseActivity.this.startActivity(addCourse);
        }
        else {
            Toast.makeText(this, "Uh oh! Looks something went wrong with creating new course. Please try again!", Toast.LENGTH_LONG).show();
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