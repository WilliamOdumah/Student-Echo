package comp3350.student_echo.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.student_echo.R;
//import comp3350.student_echo.business.AddCourseManager;
import comp3350.student_echo.objects.reviewableItems.Course;

public class AddCourseActivity extends AppCompatActivity {

    private String courseID;
    private String courseName;

    private String department;

    private Course newCourse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
    }

    public void buttonCreateNewCourse(View view) {

        Intent addCourse = new Intent(AddCourseActivity.this, ItemActivity.class);
        EditText ID = (EditText) findViewById(R.id.courseID);
        EditText name = (EditText) findViewById(R.id.courseName);
        EditText c_department = (EditText) findViewById(R.id.department);

        courseID = ID.getText().toString().toUpperCase();
        courseName = name.getText().toString();
        department = c_department.getText().toString().toUpperCase();

//        AddCourseManager addCourseManager = new AddCourseManager();
//        Course newCourse = addCourseManager.createCourse(courseID, courseName, department);


        if (newCourse != null) {
            System.out.println("Successfully created the course!");
            AddCourseActivity.this.startActivity(addCourse);
        } else {
            Toast.makeText(this, "Uh oh! Looks something went wrong with creating new course. Please try again!", Toast.LENGTH_LONG).show();
        }

    }
}