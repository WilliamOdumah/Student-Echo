package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.business.AverageCalculator;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.StudentAccount;

public class ViewInstructorActivity extends AppCompatActivity {

    private AccessReviews accessReviews;
    private List<InstructorReview> instructorReviews;
    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;
    private StudentAccount currentUser = null;
    private boolean isLoggedIn = false;
    private Instructor instructor;
    private StudentAccount loggedInAccount = null;
    private static final String ACCOUNT_KEY= "LoggedAccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_instructor);

        accessReviews = new AccessReviews();
        Intent intent = getIntent();
        instructor = (Instructor) intent.getSerializableExtra("Instructor");
        loggedInAccount= (StudentAccount)intent.getExtras().getSerializable(ACCOUNT_KEY);
        // obtain corresponding course reviews
        instructorReviews = accessReviews.getReviewFor(instructor);


        // display the course info
        TextView instructorInfoTV = findViewById(R.id.instructorInfo);
        String instructorInfo = "Instructor: "+instructor.getTitle()+". "+instructor.getFirstName()+" "+instructor.getLastName();
        instructorInfoTV.setText(instructorInfo);

        displayOverallRating();
        displayDifficultyRating();

        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsAdapter = new ReviewsAdapter(instructorReviews, currentUser, instructor);
        reviewsRecyclerView.setAdapter(reviewsAdapter);


        // Initialize the write review button and set its click listener
        Button reviewButton = findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewInstructorActivity.this, ReviewFormActivity.class);
                // Pass the current user and the review type to the intent
                intent.putExtra("CURRENT_USER", loggedInAccount);
                intent.putExtra("REVIEW_TYPE", instructor);
                // Start the ReviewFormActivity
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        // get updated reviews
        instructorReviews = accessReviews.getReviewFor(instructor);

        // update list
        reviewsAdapter.setReviews(instructorReviews, loggedInAccount);
        reviewsAdapter.notifyDataSetChanged();

        // update displayed ratings
        displayOverallRating();
        displayDifficultyRating();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayOverallRating() {
        TextView overallRatingTV = findViewById(R.id.instructorOverallRating);
        String overallRating = "Average Overall Rating: " + AverageCalculator.calcAverageOverallRating(instructorReviews) + " / 5.0";
        overallRatingTV.setText(overallRating);
    }
    private void displayDifficultyRating() {
        TextView difficultyRatingTV = findViewById(R.id.instructorDifficultyRating);
        String difficultyRating = "Average Difficulty Rating: " + AverageCalculator.calcAverageDifficultyRating(instructorReviews) + " / 5.0";
        difficultyRatingTV.setText(difficultyRating);
    }
}
