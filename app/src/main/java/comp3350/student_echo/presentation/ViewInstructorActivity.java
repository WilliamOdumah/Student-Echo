package comp3350.student_echo.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

    AccessReviews accessReviews;
    List<InstructorReview> instructorReviews;
    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;

    public static final int EDIT_REVIEW_REQUEST_CODE = 2; // Request code for editing a review

    StudentAccount currentUser = null; // the current logged in user
    StudentAccount userToBe = new StudentAccount("williamo", "password", "odumahw@myumanitoba.ca"); // stub user we will use for implementation

    private boolean isLoggedIn = false; // Flag to track if the user is logged in
    Instructor instructor;
    StudentAccount loggedInAccount = null;
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

        // display overall rating
        TextView overallRatingTV = findViewById(R.id.instructorOverallRating);
        String overallRating = "Average Overall Rating: " + AverageCalculator.calcAverageOverallRating(instructorReviews) + " / 5.0";
        overallRatingTV.setText(overallRating);

        // display difficulty rating
        TextView difficultyRatingTV = findViewById(R.id.instructorDifficultyRating);
        String difficultyRating = "Average Difficulty Rating: " + AverageCalculator.calcAverageDifficultyRating(instructorReviews) + " / 5.0";
        difficultyRatingTV.setText(difficultyRating);


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
        System.out.println("BACK TO MAIN ACTIVITY");
        // When the activity resumes, fetch the updated list of reviews
        List<InstructorReview> updatedReviews = accessReviews.getReviewFor(instructor);

        // Update the adapter with the new list and refresh the RecyclerView
        reviewsAdapter.setReviews(updatedReviews, loggedInAccount);    // with the logged in user
        reviewsAdapter.notifyDataSetChanged();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
