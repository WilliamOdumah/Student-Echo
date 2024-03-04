package comp3350.student_echo.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.business.AverageCalculator;
import comp3350.student_echo.objects.Review;

public class AverageCalculatorTest {
    List<Review> courseReviews;
    List<Review> instructorReviews;

    @Before
    public void before() {
        courseReviews = new ArrayList<>();
        instructorReviews = new ArrayList<>();

        courseReviews.add(new Review(null, "", 1, 2,null));
        courseReviews.add(new Review(null, "", 2, 2,null));
        courseReviews.add(new Review(null, "", 5, 3,null));
        courseReviews.add(new Review(null, "", 5, 3,null));

        instructorReviews.add(new Review(null, "", 1, 2,null));
        instructorReviews.add(new Review(null, "", 2, 2,null));
        instructorReviews.add(new Review(null, "", 5, 3,null));
        instructorReviews.add(new Review(null, "", 5, 3,null));
    }

    @Test
    public void testCalcAverageOverallRating() {
        // 1 + 2 + 5 + 5 = 13/4 = 3.25000...
        assertTrue("test with course reviews", isEqual(AverageCalculator.calcAverageOverallRating(courseReviews), 3.25));
        assertTrue("test with instructor reviews", isEqual(AverageCalculator.calcAverageOverallRating(instructorReviews), 3.25));
        assertTrue("test with no reviews", isEqual(AverageCalculator.calcAverageOverallRating(new ArrayList<Review>()), 0));
    }

    @Test
    public void testCalcAverageDifficultyRating() {
        // 2 + 2 + 3 + 3 = 10 / 4 = 2.5000...
        assertTrue("test with course reviews", isEqual(AverageCalculator.calcAverageDifficultyRating(courseReviews), 2.5));
        assertTrue("test with instructor reviews", isEqual(AverageCalculator.calcAverageDifficultyRating(instructorReviews), 2.5));
        assertTrue("test with no reviews", isEqual(AverageCalculator.calcAverageDifficultyRating(new ArrayList<Review>()), 0.0));
    }

    private boolean isEqual(double a, double b) {
        double epsilon = 0.00001;
        return Math.abs(a-b) < epsilon;
    }
}