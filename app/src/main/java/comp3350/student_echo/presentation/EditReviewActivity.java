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
import comp3350.student_echo.persistence.stubs.ReviewPersistenceStub;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Review;

// The activity for editing a review
public class EditReviewActivity extends AppCompatActivity {

    // UI elements and a variable to hold the current review being edited
    private Review currentReview;
    private EditText commentEditText;
    private RatingBar overallRatingBar;
    private RatingBar difficultyRatingBar;
    private Button saveButton;
    boolean isCourse = false;

    // Constant to identify the intent extra
    public static final String EXTRA_REVIEW_ID = "REVIEW_ID";

    AccessReviews accessReviews;

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

                // Call the method to update the review in the database
                if (isCourse){
                    updateCourseReviewInDatabase((CourseReview) currentReview);
                }
                else{
                    updateInstructorReviewInDatabase((InstructorReview) currentReview);
                }


                // Notify the calling activity that the edit was successful
                setResult(RESULT_OK);

                // Finish the activity and return to the previous screen
                finish();
            }
        });
    }

    private Review loadCourseReview(String reviewId) {
        // Implement the logic to retrieve a Review object given its ID
        // Here, we just simulate the retrieval from the stub database
        return accessReviews.getCourseReviewById(reviewId);
    }

    private Review loadInstructorReview(String reviewId) {
        return accessReviews.getInstructorReviewById(reviewId);
    }

    private void updateCourseReviewInDatabase(CourseReview review) {
        // Implement the logic to update a review in the database
        // Here, we just simulate the update in the stub database
        accessReviews.updateCourseReviewInDatabase(review);
    }

    private void updateInstructorReviewInDatabase(InstructorReview review) {
        accessReviews.updateInstructorReviewInDatabase(review);
    }
}
