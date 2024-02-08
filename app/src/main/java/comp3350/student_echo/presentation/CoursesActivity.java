package comp3350.student_echo.presentation;

import android.annotation.SuppressLint;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessCourses;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.StudentAccount;

public class CoursesActivity extends AppCompatActivity {

    private AccessCourses accessCourses;
    private List<Course> courseList;
    private ArrayAdapter<Course> courseArrayAdapter;

    private StudentAccount loggedInAccount;




    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Intent intent = getIntent();
        loggedInAccount= (StudentAccount)intent.getExtras().getSerializable("LoggedAccount");
        accessCourses = new AccessCourses();

        try {
            // obtain courses from DB
            courseList = accessCourses.getCourses();

            // obtain listView
            final ListView listView = (ListView) findViewById(R.id.listCourses);

            // create adapter holding Course object to display courseID and courseName
            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courseList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(courseList.get(position).getCourseID());
                    text2.setText(courseList.get(position).getCourseName());

                    return view;
                }
            };

            // set adapter for listView
            listView.setAdapter(courseArrayAdapter);

            // set onItemClickListener for listView
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                    // go to ViewCourseActivity with selected course
                    Course selectedCourse = (Course) parent.getItemAtPosition(position);
                    Intent viewCourseIntent = new Intent(CoursesActivity.this, ViewCourseActivity.class);
                    viewCourseIntent.putExtra("Course", selectedCourse);
                    viewCourseIntent.putExtra("LoggedAccount",loggedInAccount);
                    CoursesActivity.this.startActivity(viewCourseIntent);
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
                    filterCourses(searchText, listView);
                }
                @Override
                public void afterTextChanged(Editable editable) {}
            });

        } catch (final Exception e) {
            Messages.fatalError(this, e.getMessage());
        }
    }

    private void filterCourses(String searchText , ListView listView){
        ArrayList<Course> filteredCourses = new ArrayList<>();

        for (Course course: courseList){
            if (course.getCourseID().toLowerCase().contains(searchText) || course.getCourseName().toLowerCase().contains(searchText)){
                filteredCourses.add(course);

            }
        }

        ArrayAdapter<Course> filtered_adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2,android.R.id.text1,filteredCourses){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                Course course = getItem(position);
                text1.setText(course.getCourseID());
                text2.setText(course.getCourseName());

                return view;
            }
        };

        listView.setAdapter(filtered_adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_courses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonLogOutOnClick(View v){
        Intent logoutIntent= new Intent(CoursesActivity.this, LoginActivity.class);
        CoursesActivity.this.startActivity(logoutIntent);
    }
}
