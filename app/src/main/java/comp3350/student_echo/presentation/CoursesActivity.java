package comp3350.student_echo.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessCourses;
import comp3350.student_echo.objects.Course;

public class CoursesActivity extends Activity {

    private AccessCourses accessCourses;
    private List<Course> courseList;
    private ArrayAdapter<Course> courseArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        accessCourses = new AccessCourses();

        try
        {
            courseList = accessCourses.getCourses();
            courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courseList)
            {
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

            final ListView listView = (ListView)findViewById(R.id.listCourses);
            listView.setAdapter(courseArrayAdapter);
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_courses, menu);
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
                    ListView listView = (ListView)findViewById(R.id.listCourses);
                    listView.setSelection(pos);
                }
            } catch (final Exception e) {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
            Messages.warning(this, result);
        }
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
