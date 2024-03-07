package comp3350.student_echo.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.student_echo.R;
import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.business.Exceptions.InvalidInstructorException;
import comp3350.student_echo.business.access.AccessInstructors;
import comp3350.student_echo.objects.reviewableItems.Instructor;

public class AddInstructorActivity extends AppCompatActivity {

    private AccessInstructors accessInstructors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructor);
        accessInstructors = new AccessInstructors();
    }

    public void buttonCreateInstructorOnClick(View v) {
        // get info from user
        String title = ((EditText) findViewById(R.id.title_input)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.firstname_input)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.lastname_input)).getText().toString();

        // try adding instructor
        try {
            Instructor toAdd = new Instructor(title, firstName, lastName);
            accessInstructors.addInstructor(toAdd);
            // return to previous page
            finish();
        } catch(InvalidInstructorException e) {
            // show why invalid
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}