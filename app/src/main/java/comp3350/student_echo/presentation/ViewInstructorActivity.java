package comp3350.student_echo.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.business.AverageCalculator;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.StudentAccount;

public class ViewInstructorActivity extends AppCompatActivity {

    RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;
    private AccessReviews accessReviews;
    private List<InstructorReview> instructorReviews;
    private StudentAccount user;
    private Instructor instructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_instructor);

        Intent intent = getIntent();
        instructor = (Instructor) intent.getSerializableExtra("Instructor");
        accessReviews = new AccessReviews();
        instructorReviews = accessReviews.getReviewsFor(instructor);
        user = LoginManager.getLoggedInUser();

        // display the instructor info
        TextView instructorInfoTV = findViewById(R.id.instructorInfo);
        String instructorInfo = "Instructor: "+instructor.getTitle()+". "+instructor.getFirstName()+" "+instructor.getLastName();
        instructorInfoTV.setText(instructorInfo);

        // display averages
        displayOverallRating();
        displayDifficultyRating();

        // display list of reviews
        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsAdapter = new ReviewsAdapter(instructorReviews, user, instructor);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get updated reviews
        instructorReviews = accessReviews.getReviewsFor(instructor);

        // update view with current instructorReview
        reviewsAdapter = new ReviewsAdapter(instructorReviews, user, instructor);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
        displayOverallRating();
        displayDifficultyRating();
    }

    public void buttonWriteReviewOnClick(View v) {
        // go to write review page iff user is logged in
        if(user != null) {
            Intent intent = new Intent(ViewInstructorActivity.this, WriteReviewActivity.class);
            intent.putExtra("REVIEW_TYPE", instructor);
            startActivity(intent);
        }
        else {
            Toast.makeText(ViewInstructorActivity.this, "Must be logged in to write review!",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("DefaultLocale")
    private void displayOverallRating() {
        TextView overallRatingTV = findViewById(R.id.instructorOverallRating);
        double rating = AverageCalculator.calcAverageOverallRating(instructorReviews);
        String overallRating = String.format("Average Overall Rating: %.1f / 5.0", rating);
        overallRatingTV.setText(overallRating);
    }

    @SuppressLint("DefaultLocale")
    private void displayDifficultyRating() {
        TextView difficultyRatingTV = findViewById(R.id.instructorDifficultyRating);
        double rating = AverageCalculator.calcAverageDifficultyRating(instructorReviews);
        String difficultyRating = String.format("Average Difficulty Rating: %.1f / 5.0", rating);
        difficultyRatingTV.setText(difficultyRating);
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
                Intent logoutIntent= new Intent(ViewInstructorActivity.this, LoginActivity.class);
                ViewInstructorActivity.this.startActivity(logoutIntent);
                return true;
            case R.id.accountSettings:
                //to be added
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
