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
import comp3350.student_echo.business.AccessInstructors;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.Instructor;

public class InstructorActivity extends AppCompatActivity {

    private AccessInstructors accessInstructors;
    private List<Instructor> instructorList;
    private ArrayAdapter<Instructor> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors);

        accessInstructors = new AccessInstructors();
        instructorList = accessInstructors.getInstructors();

        // obtain listView
        final ListView listView = (ListView) findViewById(R.id.listInstructor);

        // set adapter for listView
        adapter = buildInstructorAdapter(instructorList);
        listView.setAdapter(adapter);

        // set listener
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // go to ViewInstructorActivity with selected instructor
                Instructor selectedInstructor = (Instructor) parent.getItemAtPosition(position);
                Intent viewInstructorIntent = new Intent(InstructorActivity.this, ViewInstructorActivity.class);
                viewInstructorIntent.putExtra("Instructor", selectedInstructor);
                InstructorActivity.this.startActivity(viewInstructorIntent);
            }
        });

        // set addTextChangedListener for search bar
        EditText instructorSearchBar = (EditText) findViewById(R.id.enter_instructor);
        instructorSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase();
                List<Instructor> filteredList = accessInstructors.filterInstructor(searchText, instructorList);
                ArrayAdapter<Instructor> filterAdapter = buildInstructorAdapter(filteredList);
                listView.setAdapter(filterAdapter);

            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private ArrayAdapter<Instructor> buildInstructorAdapter(List<Instructor> list) {
        return new ArrayAdapter<Instructor>(this,
                android.R.layout.simple_list_item_activated_2, android.R.id.text1, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(list.get(position).getFirstName() + " " + instructorList.get(position).getLastName());
                text2.setText(list.get(position).getTitle());

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
                Intent logoutIntent= new Intent(InstructorActivity.this, LoginActivity.class);
                InstructorActivity.this.startActivity(logoutIntent);
                return true;
            case R.id.accountSettings:
                //to be added
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}
