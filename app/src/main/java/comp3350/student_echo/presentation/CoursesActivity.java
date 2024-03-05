package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessCourses;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.Course;

public class CoursesActivity extends AppCompatActivity {

    private AccessCourses accessCourses;
    private List<Course> courseList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        accessCourses = new AccessCourses();
        courseList = accessCourses.getCourses();

        // obtain listView
        final ListView listView = (ListView) findViewById(R.id.listCourses);

        // set adapter for listView
        ArrayAdapter<Course> adapter = buildCourseAdapter(courseList);
        listView.setAdapter(adapter);

        // set listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // go to ViewCourseActivity with selected course
                Course selectedCourse = (Course) parent.getItemAtPosition(position);
                Intent viewCourseIntent = new Intent(CoursesActivity.this, ViewCourseActivity.class);
                viewCourseIntent.putExtra("Course", selectedCourse);
                startActivity(viewCourseIntent);
            }
        });

        // set addTextChangedListener for search bar
        EditText courseSearchBar = (EditText) findViewById(R.id.enter_course);
        courseSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase();
                List<Course> filteredList = accessCourses.filterCourses(searchText, courseList);
                ArrayAdapter<Course> filterAdapter = buildCourseAdapter(filteredList);
                listView.setAdapter(filterAdapter);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private ArrayAdapter<Course> buildCourseAdapter(List<Course> list) {
        return new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2,
                android.R.id.text1, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(list.get(position).getCourseID());
                text2.setText(list.get(position).getCourseName());

                return view;
            }
        };
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                LoginManager.performLogout();
                Intent logoutIntent= new Intent(CoursesActivity.this, LoginActivity.class);
                CoursesActivity.this.startActivity(logoutIntent);
                return true;
            case R.id.accountSettings:
                //to be added
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buttonAddCourseOnClick(View v){
        Intent newCourseIntent = new Intent(CoursesActivity.this, AddCourseActivity.class);
        CoursesActivity.this.startActivity(newCourseIntent);
    }
}
