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
import comp3350.student_echo.business.access.AccessCourses;
import comp3350.student_echo.business.access.AccessInstructors;
import comp3350.student_echo.business.access.AccessReviewableItems;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.reviewableItems.ReviewableItem;
import comp3350.student_echo.presentation.displayReviews.ReviewsForItemActivity;

public class ItemActivity extends AppCompatActivity {

    private AccessReviewableItems accessReviewableItems;
    private List<ReviewableItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        // obtain type from parent
        // NOTE: we could have used factory design pattern here to make code more extendable,
        //       but because we only have 2 types it was not worth the additional complexity.
        String type = getIntent().getStringExtra("Type");
        switch(type) {
            case "Course":
                accessReviewableItems = new AccessCourses();
                break;
            case "Instructor":
                accessReviewableItems = new AccessInstructors();
                break;
            default:
                throw new IllegalArgumentException();
        }
        itemList = accessReviewableItems.getItems();

        // set interactive functionality
        setListClickAction();
        setSearchAction();

        // display type in add button
        TextView buttonTV = (TextView) findViewById(R.id.addItem);
        String buttonText = "Add New " + type;
        buttonTV.setText(buttonText);
    }

    private void setListClickAction() {
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
    }

    private void setSearchAction() {
        // set addTextChangedListener for search bar
        EditText courseSearchBar = (EditText) findViewById(R.id.enter_search);
        ListView listView = (ListView) findViewById(R.id.listItems);
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

    public void buttonAddItemOnClick(View v) {
        // go to new page to add (can do addCourse + addInstructor, or a general addItem page)
        // Note: since entering new page, let that page deal with
        // calling access to update the DB.
    }

    private ArrayAdapter<ReviewableItem> buildAdapter(List<ReviewableItem> list) {
        return new ArrayAdapter<ReviewableItem>(this, android.R.layout.simple_list_item_activated_2,
                android.R.id.text1, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                ReviewableItem item = list.get(position);

                text1.setText(item.getPrimaryName());
                text2.setText(item.getSecondaryName());

                return view;
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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
