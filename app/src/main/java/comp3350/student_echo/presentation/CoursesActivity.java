package comp3350.student_echo.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessCourses;
import comp3350.student_echo.objects.Course;

public class CoursesActivity extends AppCompatActivity {

    private AccessCourses accessCourses;
    private List<Course> courseList;
    private ArrayAdapter<Course> courseArrayAdapter;

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

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

    public void buttonCourseCreateOnClick(View v) {
        Course course = createCourse();
        String result;

        result = validateCourseData(course, true);
        if (result == null) {
            try {
                course = accessCourses.insertCourse(course);

                courseList = accessCourses.getCourses();
                courseArrayAdapter.notifyDataSetChanged();
                int pos = courseList.indexOf(course);
                if (pos >= 0) {
                    ListView listView = (ListView) findViewById(R.id.listCourses);
                    listView.setSelection(pos);
                }
            } catch (final Exception e) {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
            Messages.warning(this, result);
        }
    }

    public void buttonBackHomeOnclick(View v) {
        onBackPressed();
    }

    private Course createCourse() {
        return new Course("ARTS", "ENGL9999", "TestNameee");
    }

    private String validateCourseData(Course course, boolean isNewCourse) {
        if (course.getCourseID().length() == 0) {
            return "Course ID required";
        }

        if (course.getCourseName().length() == 0) {
            return "Course name required";
        }

        return null;
    }

}
