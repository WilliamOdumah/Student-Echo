package comp3350.student_echo.presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessInstructors;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.Instructor;

public class InstructorActivity extends AppCompatActivity {

    private AccessInstructors accessInstructors;
    private List<Instructor> instructorList;
    private ArrayAdapter<Instructor> instructorArrayAdapter;

    private ListView filterListView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors);

        accessInstructors = new AccessInstructors();

        try
        {
            instructorList = accessInstructors.getInstructors();
            instructorArrayAdapter = new ArrayAdapter<Instructor>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, instructorList)
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

            final ListView listView = (ListView)findViewById(R.id.listInstructor);
            listView.setAdapter(instructorArrayAdapter);
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }

        EditText findInstructor = (EditText) findViewById(R.id.enter_instructor);
        filterListView = (ListView) findViewById(R.id.listInstructor);

        findInstructor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase();
                filterInstructor(searchText,filterListView);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void filterInstructor(String searchText , ListView listView){
        ArrayList<Instructor> filteredInstructor = new ArrayList<>();

        for (Instructor instructor: instructorList){
            if (instructor.getFirstName().toLowerCase().contains(searchText) || instructor.getLastName().toLowerCase().contains(searchText)){
                filteredInstructor.add(instructor);
            }
        }
        ArrayAdapter<Instructor> filtered_adapter = new ArrayAdapter<Instructor>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, filteredInstructor) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                // Use filteredInstructor list to access data directly
                text1.setText(filteredInstructor.get(position).getFirstName() + " " + filteredInstructor.get(position).getLastName());
                text2.setText(filteredInstructor.get(position).getTitle());

                return view;
            }
        };

        filterListView.setAdapter(filtered_adapter);

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
        Instructor instructor = createInstructor();
        String result;

        result = validateInstructorData(instructor, true);
        if (result == null) {
            try
            {
                instructor = accessInstructors.insertInstructor(instructor);
                if (result == null) {
                    instructorList = accessInstructors.getInstructors();
                    instructorArrayAdapter.notifyDataSetChanged();
                    int pos = instructorList.indexOf(instructor);
                    if (pos >= 0) {
                        ListView listView = (ListView) findViewById(R.id.listInstructor);
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

    private String validateInstructorData(Instructor instructor, boolean isNewInstructor) {
        if (instructor.getFirstName().length() == 0) {
            return "First name required";
        }

        if (instructor.getLastName().length() == 0) {
            return "Last name required";
        }

        return null;
    }


}
