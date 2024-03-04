package comp3350.student_echo.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.StudentAccount;

public class ViewCourseActivity extends AppCompatActivity {
    RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;
    private AccessReviews accessReviews;
    private List<CourseReview> courseReviews;
    private StudentAccount user;
    private Course course;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        Intent intent = getIntent();
        course = (Course) intent.getSerializableExtra("Course");
        accessReviews = new AccessReviews();
        courseReviews = accessReviews.getReviewsFor(course);
        user = LoginManager.getLoggedInUser();

        // display the course info
        TextView courseInfoTV = findViewById(R.id.courseInfo);
        String courseInfo = course.getCourseID() + ": " + course.getCourseName();
        courseInfoTV.setText(courseInfo);

        // display department
        TextView depTV = findViewById(R.id.courseDepartment);
        String courseDept = "Department: " + course.getDepartment();
        depTV.setText(courseDept);

        // display averages
        displayOverallRating();
        displayDifficultyRating();

        // display list of reviews via adapter
        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsAdapter = new ReviewsAdapter(courseReviews, user, course);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get updated reviews
        courseReviews = accessReviews.getReviewsFor(course);

        // update view with current courseReviews
        reviewsAdapter = new ReviewsAdapter(courseReviews, user, course);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
        displayOverallRating();
        displayDifficultyRating();
    }

    public void buttonWriteReviewOnClick(View v) {
        // go to write review page iff user is logged in
        if(user != null) {
            Intent intent = new Intent(ViewCourseActivity.this, WriteReviewActivity.class);
            intent.putExtra("REVIEW_TYPE", course);
            startActivity(intent);
        }
        else {
            Toast.makeText(ViewCourseActivity.this, "Must be logged in to write review!",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("DefaultLocale")
    private void displayOverallRating() {
        TextView overallRatingTV = findViewById(R.id.courseOverallRating);
        double rating = AverageCalculator.calcAverageOverallRating(courseReviews);
        String overallRating = String.format("Average Overall Rating: %.1f / 5.0", rating);
        overallRatingTV.setText(overallRating);
    }

    @SuppressLint("DefaultLocale")
    private void displayDifficultyRating() {
        TextView difficultyRatingTV = findViewById(R.id.courseDifficultyRating);
        double rating = AverageCalculator.calcAverageDifficultyRating(courseReviews);
        String difficultyRating = String.format("Average Difficulty Rating: %.1f / 5.0", rating);
        difficultyRatingTV.setText(difficultyRating);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                LoginManager.performLogout();
                Intent logoutIntent= new Intent(ViewCourseActivity.this, LoginActivity.class);
                ViewCourseActivity.this.startActivity(logoutIntent);
                return true;
            case R.id.accountSettings:
                //to be added
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
