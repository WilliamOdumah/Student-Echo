package comp3350.student_echo.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.business.AverageCalculator;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;

public class AverageCalculatorTest {
    List<CourseReview> courseReviews;
    List<InstructorReview> instructorReviews;

    @Before
    public void before() {
        courseReviews = new ArrayList<>();
        instructorReviews = new ArrayList<>();

        courseReviews.add(new CourseReview(null, "", 1, 2));
        courseReviews.add(new CourseReview(null, "", 2, 2));
        courseReviews.add(new CourseReview(null, "", 5, 3));
        courseReviews.add(new CourseReview(null, "", 5, 3));

        instructorReviews.add(new InstructorReview(null, "", 1, 2));
        instructorReviews.add(new InstructorReview(null, "", 2, 2));
        instructorReviews.add(new InstructorReview(null, "", 5, 3));
        instructorReviews.add(new InstructorReview(null, "", 5, 3));
    }

    @Test
    public void testCalcAverageOverallRating() {
        // 1 + 2 + 5 + 5 = 13/4 = 3.25 -->round--> 3.3
        assertEquals("test with course reviews", AverageCalculator.calcAverageOverallRating(courseReviews), "3.3");
        assertEquals("test with instructor reviews", AverageCalculator.calcAverageOverallRating(instructorReviews), "3.3");
        assertEquals("test with no reviews", AverageCalculator.calcAverageOverallRating(new ArrayList<CourseReview>()), "0.0");
    }

    @Test
    public void testCalcAverageDifficultyRating() {
        // 2 + 2 + 3 + 3 = 10 / 4 = 2.5
        assertEquals("test with course reviews", AverageCalculator.calcAverageDifficultyRating(courseReviews), "2.5");
        assertEquals("test with instructor reviews", AverageCalculator.calcAverageDifficultyRating(instructorReviews), "2.5");
        assertEquals("test with no reviews", AverageCalculator.calcAverageDifficultyRating(new ArrayList<CourseReview>()), "0.0");
    }
}