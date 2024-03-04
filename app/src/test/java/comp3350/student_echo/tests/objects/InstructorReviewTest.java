package comp3350.student_echo.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.objects.reviews.InstructorReview;
import comp3350.student_echo.objects.reviews.Review;
import comp3350.student_echo.objects.StudentAccount;

public class InstructorReviewTest {
    private Instructor instructor;
    private StudentAccount author;
    private Review instructorReview;

    @Before
    public void before() {
        instructor = new Instructor("Internet Friend", "Rob", "Guderian");
        author = new StudentAccount("kelly", "villamayor", "villamak@myumanitoba.ca");
        instructorReview = new InstructorReview(instructor, "a very good farmer", 4, 3, author);
    }

    @Test
    public void testGetDifficultyRating() {
        assertEquals("checking instructor review's difficulty rating", instructorReview.getDifficultyRating(), 3);
        assertNotEquals("checking instructor review's difficulty rating", instructorReview.getDifficultyRating(), 1);
    }

    @Test
    public void testGetWrittenBy() {
        assertEquals("checking instructor review's author", instructorReview.getAuthorUsername(), "kelly");
        assertNotEquals("checking instructor review's author", instructorReview.getAuthorUsername(), "a");
    }

    @Test
    public void testGetComment() {
        assertEquals("checking instructor review's comment", instructorReview.getComment(), "a very good farmer");
        assertNotEquals("checking instructor review's comment", instructorReview.getComment(), "empty");
    }

    @Test
    public void testGetOverallRating() {
        assertEquals("checking instructor review's overall rating", instructorReview.getOverallRating(), 4);
        assertNotEquals("checking instructor review's overall rating", instructorReview.getOverallRating(), 1);
    }
}