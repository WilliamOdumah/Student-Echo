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
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.StudentAccount;

public class ViewCourseActivity extends AppCompatActivity {

    AccessReviews accessReviews;
    List<CourseReview> courseReviews;
    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;

    public static final int EDIT_REVIEW_REQUEST_CODE = 2; // Request code for editing a review

   // Variables for user account information
    StudentAccount currentUser = null; // the current logged in user
    StudentAccount userToBe = new StudentAccount("williamo", "password", "odumahw@myumanitoba.ca"); // stub user we will use for implementation

    StudentAccount loggedInAccount = null;
    private static final String ACCOUNT_KEY= "LoggedAccount";
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        accessReviews = new AccessReviews();
        Intent intent = getIntent();
        course = (Course) intent.getSerializableExtra("Course");
        loggedInAccount= (StudentAccount)intent.getExtras().getSerializable(ACCOUNT_KEY);


        courseReviews = accessReviews.getReviewFor(course);

        // display the course info
        TextView courseInfoTV = findViewById(R.id.courseInfo);
        String courseInfo = course.getCourseID() + ": " + course.getCourseName();
        courseInfoTV.setText(courseInfo);

        // display department
        TextView depTV = findViewById(R.id.courseDepartment);
        String courseDept = "Department: " + course.getDepartment();
        depTV.setText(courseDept);

        // display overall rating
        TextView overallRatingTV = findViewById(R.id.courseOverallRating);
        String overallRating = "Average Overall Rating: " + AverageCalculator.calcAverageOverallRating(courseReviews) + " / 5.0";
        overallRatingTV.setText(overallRating);

        // display difficulty rating
        TextView difficultyRatingTV = findViewById(R.id.courseDifficultyRating);
        String difficultyRating = "Average Difficulty Rating: " + AverageCalculator.calcAverageDifficultyRating(courseReviews) + " / 5.0";
        difficultyRatingTV.setText(difficultyRating);


        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsAdapter = new ReviewsAdapter(courseReviews, currentUser, course);
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        // Initialize the write review button and set its click listener
        Button reviewButton = findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewCourseActivity.this, ReviewFormActivity.class);
                // Pass the current user and the review type to the intent
                intent.putExtra("CURRENT_USER", loggedInAccount);
                intent.putExtra("REVIEW_TYPE", course);
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
        List<CourseReview> updatedReviews = accessReviews.getReviewFor(course);
        if (!updatedReviews.isEmpty()){
            System.out.println("IN THE LIST OF REVIEWS WE HAVE"+updatedReviews.get(0).getComment());
        }
        else {
            System.out.println("THE LIST IS STILL EMPTY");
        }
        // Update the adapter with the new list and refresh the RecyclerView
        reviewsAdapter.setReviews(updatedReviews, loggedInAccount);    // make sure to use the logged in user
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
