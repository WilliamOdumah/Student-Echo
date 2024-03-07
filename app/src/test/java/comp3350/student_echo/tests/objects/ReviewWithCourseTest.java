package comp3350.student_echo.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;

public class ReviewWithCourseTest {
    private Course course;
    private StudentAccount author;
    private Review courseReview;

    @Before
    public void before() {
        course = new Course("Science", "COMP3010", "Distributed Systems");
        author = new StudentAccount("kelly", "villamayor", "villamak@myumanitoba.ca");
        courseReview = new Review(course,"mid", 3, 4, author);
    }

    @Test
    public void testGetDifficultyRating() {
        assertEquals("checking course review's difficulty rating", courseReview.getDifficultyRating(), 4);
        assertNotEquals("checking course review's difficulty rating", courseReview.getDifficultyRating(), 1);
    }

    @Test
    public void testGetWrittenBy() {
        assertEquals("checking course review's author", courseReview.getAuthorUsername(), "kelly");
        assertNotEquals("checking course review's author", courseReview.getAuthorUsername(), "a");
    }

    @Test
    public void testGetComment() {
        assertEquals("checking course review's comment", courseReview.getComment(), "mid");
        assertNotEquals("checking course review's comment", courseReview.getComment(), "notmid");
    }

    @Test
    public void testGetOverallRating() {
        assertEquals("checking course review's overall rating", courseReview.getOverallRating(), 3);
        assertNotEquals("checking course review's overall rating", courseReview.getOverallRating(), 1);
    }
}