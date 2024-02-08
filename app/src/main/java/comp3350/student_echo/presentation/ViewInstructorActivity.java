package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.StudentAccount;

public class ViewInstructorActivity extends AppCompatActivity {

    private StudentAccount loggedInAccount;
    AccessReviews accessReviews;
    List<InstructorReview> instructorReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        loggedInAccount= (StudentAccount)intent.getExtras().getSerializable("LoggedAccount");

        setContentView(R.layout.activity_view_instructor);

        accessReviews = new AccessReviews();

        try {
            // obtain course from prev Activity
            Instructor instructor = (Instructor) intent.getSerializableExtra("Instructor");

            // obtain corresponding course reviews
            instructorReviews = accessReviews.getReviewFor(instructor);

            // display the course info
            TextView instructorInfoTV = findViewById(R.id.instructorInfo);
            String instructorInfo = "Instructor: "+instructor.getTitle()+". "+instructor.getFirstName()+" "+instructor.getLastName();
            instructorInfoTV.setText(instructorInfo);

            // display average rating
            TextView averageRatingTV = findViewById(R.id.instructorRating);
            String avRating = "Average Rating: " + instructor.getAverageRating() + " / 5.0";
            averageRatingTV.setText(avRating);

            // display reviews in list
            // obtain listView
            final ListView listView = (ListView) findViewById(R.id.listReviews);

            // create adapter holding CourseReview object to display Review and overallRating
            ArrayAdapter<InstructorReview> reviewArrayAdapter = new ArrayAdapter<InstructorReview>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, instructorReviews) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(instructorReviews.get(position).getComment());
                    text2.setText(String.valueOf(instructorReviews.get(position).getOverallRating()));

                    return view;
                }
            };

            // set adapter for listView
            listView.setAdapter(reviewArrayAdapter);
        }
        catch (Exception e) {
            Messages.fatalError(this, e.getMessage());
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}