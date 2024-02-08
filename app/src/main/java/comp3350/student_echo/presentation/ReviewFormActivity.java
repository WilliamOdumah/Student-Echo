package comp3350.student_echo.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.persistence.stubs.ReviewPersistenceStub;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;

public class ReviewFormActivity extends AppCompatActivity {

    private RatingBar overallRatingBar;
    private RatingBar difficultyRatingBar;
    private EditText commentEditText;

    private boolean isCourse=false;
//    ReviewPersistenceStub database = ReviewPersistenceStub.getInstance(); //get the database
    Course currentCourse = null;
    Instructor currentInstructor = null;
    AccessReviews accessReviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_form);

        // Retrieve the StudentAccount object
        StudentAccount currentUser = (StudentAccount) getIntent().getSerializableExtra("CURRENT_USER");

        accessReviews = new AccessReviews();

        Object current = getIntent().getSerializableExtra("REVIEW_TYPE");

        if (current instanceof Course){
            currentCourse = (Course) getIntent().getSerializableExtra("REVIEW_TYPE");
            isCourse = true;
        } else if (current instanceof Instructor) {
            currentInstructor = (Instructor) getIntent().getSerializableExtra("REVIEW_TYPE");
        }
//        Course currentCourse = (Course) getIntent().getSerializableExtra("REVIEW_TYPE");
        if (currentUser == null) {
            Toast.makeText(this, "User is not logged in.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        overallRatingBar = findViewById(R.id.overallRatingBar);
        difficultyRatingBar = findViewById(R.id.difficultyRatingBar);
        commentEditText = findViewById(R.id.commentEditText);

        Button submitReviewButton = findViewById(R.id.submitReviewButton);
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("SUBMIT CLICKED");
                // Get values from form
                int overallRating = (int) overallRatingBar.getRating();
                int difficultyRating = (int) difficultyRatingBar.getRating();
                String comment = commentEditText.getText().toString();

                // Create Review object and save to database
                if (isCourse){
                    System.out.println("IS A COURSE");
                    CourseReview review = new CourseReview(currentCourse, comment, overallRating, difficultyRating, currentUser);
//                    database.addCourseReview(review);
                    accessReviews.addCourseReview(review);
                }
                else {
                    System.out.println("IS AN INSTRUCTOR");
                    InstructorReview review = new InstructorReview(currentInstructor, comment, overallRating, difficultyRating, currentUser);
//                    database.addInstructorReview(review);
                    accessReviews.addInstructorReview(review);
                    if (accessReviews.getReviewFor(currentInstructor).isEmpty()){
                        System.out.println("ITS STILL EMPTY");
                    }
                    else {
                        System.out.println("FOR INSTRUCTOR = "+currentInstructor.getFirstName());
                        System.out.println("INSTRUCTOR REVIEW HAS BEEN ADDED. WE HAVE"+accessReviews.getReviewFor(currentInstructor).get(0).getComment());
                    }
                }

                // Return to the main activity
                finish();
            }
        });
    }
}

