package comp3350.student_echo.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.objects.Review;

public class EditReviewActivity extends AppCompatActivity {
    EditText commentEditText;
    RatingBar overallRatingBar;
    RatingBar difficultyRatingBar;
    Button saveButton;
    Review review;

    private AccessReviews accessReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);
        accessReviews = new AccessReviews();

        // Link the UI elements from the layout to the variables
        commentEditText = findViewById(R.id.commentEditText);
        overallRatingBar = findViewById(R.id.overallRatingBar);
        difficultyRatingBar = findViewById(R.id.difficultyRatingBar);
        saveButton = findViewById(R.id.saveButton);

        // Get review data from intent
        review = (Review) getIntent().getSerializableExtra("REVIEW");

        // Populate the UI elements with review data
        commentEditText.setText(review.getComment());
        overallRatingBar.setRating(review.getOverallRating());
        difficultyRatingBar.setRating(review.getDifficultyRating());

        // Set an OnClickListener for the save button to update the review
        saveButton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update review with the new values from UI elements
                review.setComment(commentEditText.getText().toString());
                review.setOverallRating((int) overallRatingBar.getRating());
                review.setDifficultyRating((int) difficultyRatingBar.getRating());

                accessReviews.updateReviewInDatabase(review);
                // Notify the calling activity that the edit was successful
                setResult(RESULT_OK);

                // Finish the activity and return to the previous screen
                finish();
            }
        });
    }

}
