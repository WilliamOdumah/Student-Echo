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
import comp3350.student_echo.business.AccessInstructors;
import comp3350.student_echo.objects.Instructor;

public class InstructorActivity extends Activity {

    private AccessInstructors accessInstructors;
    private List<Instructor> instructorList;
    private ArrayAdapter<Instructor> studentArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors);

        accessInstructors = new AccessInstructors();

        try
        {
            instructorList = accessInstructors.getInstructors();
            studentArrayAdapter = new ArrayAdapter<Instructor>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, instructorList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(instructorList.get(position).getFirstName() + " " + instructorList.get(position).getLastName());
                    text2.setText(instructorList.get(position).getTitle());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listStudents);
            listView.setAdapter(studentArrayAdapter);
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_students, menu);
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

    public void buttonInstructorCreateOnClick(View v) {
        Instructor student = createInstructor();
        String result;

        result = validateStudentData(student, true);
        if (result == null) {
            try
            {
                student = accessInstructors.insertStudent(student);
                if (result == null) {
                    instructorList = accessInstructors.getInstructors();
                    studentArrayAdapter.notifyDataSetChanged();
                    int pos = instructorList.indexOf(student);
                    if (pos >= 0) {
                        ListView listView = (ListView) findViewById(R.id.listStudents);
                        listView.setSelection(pos);
                    }
                }
            }
            catch(final Exception e)
            {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
        	Messages.warning(this, result);
        }
    }

    private Instructor createInstructor() {
        return new Instructor("101", "PROF TEST", "ADDDRRR");
    }

    private String validateStudentData(Instructor instructor, boolean isNewInstructor) {
        if (instructor.getFirstName().length() == 0) {
            return "First name required";
        }

        if (instructor.getLastName().length() == 0) {
            return "Last name required";
        }

        return null;
    }
}
