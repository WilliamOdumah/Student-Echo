package comp3350.student_echo.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.StudentAccount;

public class ViewCourseActivity extends AppCompatActivity {

    AccessReviews accessReviews;
    List<CourseReview> courseReviews;
    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;

    public static final int EDIT_REVIEW_REQUEST_CODE = 2; // Request code for editing a review

//    // Variables for user account information
    StudentAccount currentUser = null; // the current logged in user
    StudentAccount userToBe = new StudentAccount("williamo", "password", "odumahw@myumanitoba.ca"); // stub user we will use for implementation

//    StudentAccount loggedInAccount = null;
//    private static final String ACCOUNT_KEY= "LoggedAccount";
    Course course;
    private boolean isLoggedIn = false; // Flag to track if the user is logged in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        accessReviews = new AccessReviews();
        Intent intent = getIntent();
        course = (Course) intent.getSerializableExtra("Course");

        courseReviews = accessReviews.getReviewFor(course);

        TextView courseInfoTV = findViewById(R.id.courseInfo);
        TextView depTV = findViewById(R.id.courseDepartment);
        TextView averageRatingTV = findViewById(R.id.courseRating);

        courseInfoTV.setText(course.getCourseID() + ": " + course.getCourseName());
        depTV.setText("Department: " + course.getDepartment());
        averageRatingTV.setText("Average Rating: " + course.getAverageRating() + " / 5.0");

        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsAdapter = new ReviewsAdapter(courseReviews, currentUser, course);
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        // Initialize the write review button and set its click listener
        Button reviewButton = findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the user is logged in
                if (isLoggedIn) {
                    // Set the currentUser to the userToBe object if the user is logged in
                    currentUser = userToBe;
                    // Create an intent to start the ReviewFormActivity
                    Intent intent = new Intent(ViewCourseActivity.this, ReviewFormActivity.class);
                    // Pass the current user and the review type to the intent
                    intent.putExtra("CURRENT_USER", currentUser);
                    intent.putExtra("REVIEW_TYPE", course);
                    // Start the ReviewFormActivity
                    startActivity(intent);
                } else {
                    // If the user is not logged in, show the login prompt
                    showLoginPrompt();
                }
            }
        });
    }

    private void showLoginPrompt() {
        // Create an AlertDialog to prompt the user to log in
        new AlertDialog.Builder(this)
                .setTitle("Login Required")
                .setMessage("You must be logged in to write a review.")
                .setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Log the user in and set the isLoggedIn flag to true
                        isLoggedIn = true;
                        // Set the currentUser to the userToBe object
                        currentUser = userToBe;
                        // Show a Toast message indicating the user has logged in
                        Toast.makeText(ViewCourseActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // If the user cancels, dismiss the dialog
                        dialogInterface.dismiss();
                    }
                })
                .show();
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
        reviewsAdapter.setReviews(updatedReviews, userToBe);    // make sure to use the logged in user
        reviewsAdapter.notifyDataSetChanged();
    }
}
