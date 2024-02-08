package comp3350.student_echo.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Review;

public class EditReviewActivity extends AppCompatActivity {

    private Review currentReview;
    private EditText commentEditText;
    private RatingBar overallRatingBar;
    private RatingBar difficultyRatingBar;
    private Button saveButton;
    private boolean isCourse = false;
    private AccessReviews accessReviews;
    public static final String EXTRA_REVIEW_ID = "REVIEW_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout defined in activity_edit_review.xml
        setContentView(R.layout.activity_edit_review);

        accessReviews = new AccessReviews();

        // Link the UI elements from the layout to the variables
        commentEditText = findViewById(R.id.commentEditText);
        overallRatingBar = findViewById(R.id.overallRatingBar);
        difficultyRatingBar = findViewById(R.id.difficultyRatingBar);
        saveButton = findViewById(R.id.saveButton);

        // Retrieve the review ID passed as an extra in the intent
        String reviewId = getIntent().getStringExtra(EXTRA_REVIEW_ID);
        Object type = getIntent().getSerializableExtra("REVIEW_TYPE");;

        // Using the review ID, retrieve the corresponding Review object from the database
        if (type instanceof Course){
            currentReview = loadCourseReview(reviewId);
            isCourse = true;
        }
        else {
            currentReview = loadInstructorReview(reviewId);
        }

        // Populate the UI elements with the data from the retrieved Review object
        commentEditText.setText(currentReview.getComment());
        overallRatingBar.setRating(currentReview.getOverallRating());
        difficultyRatingBar.setRating(currentReview.getDifficultyRating());

        // Set an OnClickListener for the save button to update the review
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the currentReview object with the new values from UI elements
                currentReview.setComment(commentEditText.getText().toString());
                currentReview.setOverallRating((int) overallRatingBar.getRating());
                currentReview.setDifficultyRating((int) difficultyRatingBar.getRating());

                // prompt database to update
                if (isCourse) {
                    accessReviews.updateCourseReviewInDatabase((CourseReview) currentReview);
                }
                else {
                    accessReviews.updateInstructorReviewInDatabase((InstructorReview) currentReview);
                }


                // Notify the calling activity that the edit was successful
                setResult(RESULT_OK);

                // Finish the activity and return to the previous screen
                finish();
            }
        });
    }

    private Review loadCourseReview(String reviewId) {
        return accessReviews.getCourseReviewById(reviewId);
    }

    private Review loadInstructorReview(String reviewId) {
        return accessReviews.getInstructorReviewById(reviewId);
    }

}
