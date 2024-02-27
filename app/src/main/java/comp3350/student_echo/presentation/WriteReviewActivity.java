package comp3350.student_echo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.business.LoginManager;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;

public class WriteReviewActivity extends AppCompatActivity {
    private AccessReviews accessReviews;
    private StudentAccount user;
    private Course course;
    private Instructor instructor;
    private boolean isCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_form);
        accessReviews = new AccessReviews();

        // First must ensure user is logged in
        user = LoginManager.getLoggedInUser();
        if(user == null) {
            Toast.makeText(this, "User is not logged in.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Obtain intent info
        Object type = getIntent().getSerializableExtra("REVIEW_TYPE");
        if (type instanceof Course){
            course = (Course) type;
            isCourse = true;
        } else if (type instanceof Instructor) {
            instructor = (Instructor) type;
        }

        // link to view variables
        RatingBar overallRatingBar = findViewById(R.id.overallRatingBar);
        RatingBar difficultyRatingBar = findViewById(R.id.difficultyRatingBar);
        EditText commentEditText = findViewById(R.id.commentEditText);
        Button submitReviewButton = findViewById(R.id.submitReviewButton);

        // set listener
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from form
                int overallRating = (int) overallRatingBar.getRating();
                int difficultyRating = (int) difficultyRatingBar.getRating();
                String comment = commentEditText.getText().toString();

                // Create Review object and save to database
                Review newReview;
                if (isCourse){
                    newReview = new CourseReview(course, comment, overallRating, difficultyRating, user);
                }
                else {
                    newReview = new InstructorReview(instructor, comment, overallRating, difficultyRating, user);
                }
                accessReviews.addReview(newReview);

                // Return to the main activity
                finish();
            }
        });
    }
}

