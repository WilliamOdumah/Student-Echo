package comp3350.student_echo.presentation.displayReviews;

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

import java.io.Serializable;
import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.access.AccessReviews;
import comp3350.student_echo.business.AverageCalculator;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.reviewableItems.ReviewableItem;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.presentation.LoginActivity;

public class ReviewsForItemActivity extends AppCompatActivity implements ReviewModificationListener {
    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;
    private AccessReviews accessReviews;
    private List<Review> reviewList;
    private StudentAccount user;
    private ReviewableItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_for_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        item = (ReviewableItem) intent.getSerializableExtra("Item");
        accessReviews = new AccessReviews();
        reviewList = accessReviews.getReviewsFor(item);
        user = LoginManager.getLoggedInUser();

        // display info
        TextView itemInfoTV = findViewById(R.id.itemInfo);
        String itemInfo = item.getDisplayInfo();
        itemInfoTV.setText(itemInfo);

        // display department
        TextView depTV = findViewById(R.id.department);
        String department = "Department: " + item.getDepartment();
        depTV.setText(department);

        // display averages
        displayOverallRating();
        displayDifficultyRating();

        // display list of reviews via adapter
        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsAdapter = new ReviewsAdapter(reviewList, user, this, accessReviews);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        // build adapter with new data
        reviewList = accessReviews.getReviewsFor(item);
        reviewsAdapter = new ReviewsAdapter(reviewList, user,this, accessReviews);
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        // update ratings
        displayOverallRating();
        displayDifficultyRating();
    }

    @Override
    public void onReviewDeletion(int position) {
        // delete in database
        accessReviews.deleteReview(reviewList.get(position));

        // update view
        reviewList.remove(position);
        reviewsAdapter.notifyItemRemoved(position);

        // update ratings
        displayOverallRating();
        displayDifficultyRating();

        // notify user
        Toast.makeText(this, "Review deleted", Toast.LENGTH_SHORT).show();
    }

    public void buttonWriteReviewOnClick(View v) {
        // go to write review page iff user is logged in
        if(user != null) {
            Intent intent = new Intent(ReviewsForItemActivity.this, WriteReviewActivity.class);
            intent.putExtra("Item", (Serializable) item);
            startActivity(intent);
        }
        else {
            Toast.makeText(ReviewsForItemActivity.this, "Must be logged in to write review!",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("DefaultLocale")
    private void displayOverallRating() {
        TextView overallRatingTV = findViewById(R.id.courseOverallRating);
        double rating = AverageCalculator.calcAverageOverallRating(reviewList);
        String overallRating = String.format("Average Overall Rating: %.1f / 5.0", rating);
        overallRatingTV.setText(overallRating);
    }

    @SuppressLint("DefaultLocale")
    private void displayDifficultyRating() {
        TextView difficultyRatingTV = findViewById(R.id.courseDifficultyRating);
        double rating = AverageCalculator.calcAverageDifficultyRating(reviewList);
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
                Intent logoutIntent= new Intent(ReviewsForItemActivity.this, LoginActivity.class);
                ReviewsForItemActivity.this.startActivity(logoutIntent);
                return true;
            case R.id.accountSettings:
                //to be added
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
