package comp3350.student_echo.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.business.AverageCalculator;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Review;

public class ViewCourseActivity extends AppCompatActivity {

    AccessReviews accessReviews;
    List<CourseReview> courseReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        accessReviews = new AccessReviews();

        try {
            // obtain course from prev Activity
            Intent intent = getIntent();
            Course course = (Course) intent.getSerializableExtra("Course");

            // obtain corresponding course reviews
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

            // display reviews in list
            // obtain listView
            final ListView listView = (ListView) findViewById(R.id.listReviews);

            // create adapter holding CourseReview object to display Review and overallRating
            ArrayAdapter<CourseReview> reviewArrayAdapter = new ArrayAdapter<CourseReview>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, courseReviews) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                    //TextView text3 = (TextView) view.findViewById(android.R.id.text3);

                    text1.setText(courseReviews.get(position).getComment());
                    text2.setText(String.valueOf(courseReviews.get(position).getOverallRating()));

                    return view;
                }
            };

            // set adapter for listView
            listView.setAdapter(reviewArrayAdapter);


        }
        catch (Exception e) {
            Messages.fatalError(this, e.getMessage());
        }
    }
}