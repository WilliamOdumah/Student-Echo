package comp3350.student_echo.presentation.displayReviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.access.AccessReviews;
import comp3350.student_echo.objects.reviews.Review;

public class EditReviewActivity extends AppCompatActivity {
    private AccessReviews accessReviews;
    private EditText commentEditText;
    private RatingBar overallRatingBar;
    private RatingBar difficultyRatingBar;
    private Review review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        accessReviews = new AccessReviews();

        // Link the UI elements from the layout to the variables
        commentEditText = findViewById(R.id.commentEditText);
        overallRatingBar = findViewById(R.id.overallRatingBar);
        difficultyRatingBar = findViewById(R.id.difficultyRatingBar);

        // Get review data from intent
        Intent intent = getIntent();
        review = (Review) intent.getSerializableExtra("REVIEW");

        // Populate the UI elements with review data
        commentEditText.setText(review.getComment());
        overallRatingBar.setRating(review.getOverallRating());
        difficultyRatingBar.setRating(review.getDifficultyRating());
    }

    public void buttonSaveChangesOnClick(View v) {
        // Update review with the new values from UI elements
        review.setComment(commentEditText.getText().toString());
        review.setOverallRating((int) overallRatingBar.getRating());
        review.setDifficultyRating((int) difficultyRatingBar.getRating());

        // update DB
        accessReviews.updateReviewInDatabase(review);

        // Notify the calling activity that the edit was successful
        setResult(RESULT_OK);

        // Finish the activity and return to the previous screen
        finish();
    }

}
