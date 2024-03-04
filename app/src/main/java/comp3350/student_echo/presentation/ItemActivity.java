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

import java.io.Serializable;
import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessCourses;
import comp3350.student_echo.business.AccessInstructors;
import comp3350.student_echo.business.AccessReviewableItems;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.ReviewableItem;

public class ItemActivity extends AppCompatActivity {

    private AccessReviewableItems accessReviewableItems;
    private List<ReviewableItem> itemList;
    private String type;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        type = intent.getStringExtra("Type");
        if(type.equals("Course")) {
            accessReviewableItems = new AccessCourses();
        } else if(type.equals("Instructor")) {
            accessReviewableItems = new AccessInstructors();
        }

        itemList = accessReviewableItems.getItems();

        // obtain listView
        final ListView listView = (ListView) findViewById(R.id.listItems);

        // set adapter for listView
        ArrayAdapter<ReviewableItem> adapter = buildAdapter(itemList);
        listView.setAdapter(adapter);

        // set listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // go to ReviewsForItemActivity with selected Item
                ReviewableItem selectedItem = (ReviewableItem) parent.getItemAtPosition(position);
                Intent viewReviewsIntent = new Intent(ItemActivity.this, ReviewsForItemActivity.class);
                viewReviewsIntent.putExtra("Item", (Serializable) selectedItem);
                startActivity(viewReviewsIntent);
            }
        });

        // set addTextChangedListener for search bar
        EditText courseSearchBar = (EditText) findViewById(R.id.enter_search);
        courseSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase();
                List<ReviewableItem> filteredList = accessReviewableItems.filter(searchText, itemList);
                ArrayAdapter<ReviewableItem> filterAdapter = buildAdapter(filteredList);
                listView.setAdapter(filterAdapter);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private ArrayAdapter<ReviewableItem> buildAdapter(List<ReviewableItem> list) {
        if(type.equals("Course")) {
            return buildCourseAdapter(list);
        } else if(type.equals("Instructor")) {
            return buildInstructorAdapter(list);
        }
        return null;
    }

    private ArrayAdapter<ReviewableItem> buildCourseAdapter(List<ReviewableItem> list) {
        return new ArrayAdapter<ReviewableItem>(this, android.R.layout.simple_list_item_activated_2,
                android.R.id.text1, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                Course c = (Course) list.get(position);

                text1.setText(c.getCourseID());
                text2.setText(c.getCourseName());

                return view;
            }
        };
    }
    private ArrayAdapter<ReviewableItem> buildInstructorAdapter(List<ReviewableItem> list) {
        return new ArrayAdapter<ReviewableItem>(this,
                android.R.layout.simple_list_item_activated_2, android.R.id.text1, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                Instructor instructor = (Instructor) list.get(position);
                text1.setText(instructor.getFirstName() + " " + instructor.getLastName());
                text2.setText(instructor.getTitle());

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
                Intent logoutIntent= new Intent(ItemActivity.this, LoginActivity.class);
                ItemActivity.this.startActivity(logoutIntent);
                return true;
            case R.id.accountSettings:
                //to be added
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
