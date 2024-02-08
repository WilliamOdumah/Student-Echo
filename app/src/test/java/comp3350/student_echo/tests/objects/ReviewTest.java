package comp3350.student_echo.tests.objects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;

public class ReviewTest {

    private Course course;
    private Instructor instructor;
    private StudentAccount author;
    private Review courseReview;
    private Review instructorReview;

    @Before
    public void before() {
        course = new Course("Science", "COMP3010", "Distributed Systems");
        instructor = new Instructor("Internet Friend", "Rob", "Guderian");
        author = new StudentAccount("kelly", "villamayor", "villamak@myumanitoba.ca");
        courseReview = new CourseReview(course,"mid", 3, 4, author);
        instructorReview = new InstructorReview(instructor, "a very good farmer", 4, 3, author);
    }

    @Test
    public void testGetDifficultyRating() {
        assertEquals("checking course review's difficulty rating", courseReview.getDifficultyRating(), 4);
        assertNotEquals("checking course review's difficulty rating", courseReview.getDifficultyRating(), 1);

        assertEquals("checking instructor review's difficulty rating", instructorReview.getDifficultyRating(), 3);
        assertNotEquals("checking instructor review's difficulty rating", instructorReview.getDifficultyRating(), 1);
    }

    @Test
    public void testGetWrittenBy() {
        assertEquals("checking course review's author", courseReview.getWrittenBy(), "kelly");
        assertNotEquals("checking course review's author", courseReview.getWrittenBy(), "a");

        assertEquals("checking instructor review's author", instructorReview.getWrittenBy(), "kelly");
        assertNotEquals("checking instructor review's author", instructorReview.getWrittenBy(), "a");
    }

    @Test
    public void testGetComment() {
        assertEquals("checking course review's comment", courseReview.getComment(), "mid");
        assertNotEquals("checking course review's comment", courseReview.getComment(), "notmid");

        assertEquals("checking instructor review's comment", instructorReview.getComment(), "a very good farmer");
        assertNotEquals("checking instructor review's comment", instructorReview.getComment(), "empty");
    }

    @Test
    public void testGetOverallRating() {
        assertEquals("checking course review's overall rating", courseReview.getOverallRating(), 3);
        assertNotEquals("checking course review's overall rating", courseReview.getOverallRating(), 1);

        assertEquals("checking instructor review's overall rating", instructorReview.getOverallRating(), 4);
        assertNotEquals("checking instructor review's overall rating", instructorReview.getOverallRating(), 1);
    }

    @Test
    public void testSetComment() {
        courseReview.setComment("changed comment");
        assertEquals("setting course review's comment", courseReview.getComment(), "changed comment");
        assertNotEquals("setting course review's comment", courseReview.getComment(), "wrong comment");

        instructorReview.setComment("changed comment");
        assertEquals("setting instructor review's comment", instructorReview.getComment(), "changed comment");
        assertNotEquals("setting instructor review's comment", instructorReview.getComment(), "wrong comment");
    }

    @Test
    public void testSetOverallRating() {
        courseReview.setOverallRating(1);
        assertEquals("setting course review's overall rating", courseReview.getOverallRating(), 1);
        assertNotEquals("setting course review's overall rating", courseReview.getOverallRating(), 5);

        instructorReview.setOverallRating(1);
        assertEquals("setting instructor review's overall rating", instructorReview.getOverallRating(), 1);
        assertNotEquals("setting instructor review's overall rating", instructorReview.getOverallRating(), 5);
    }

    @Test
    public void testSetDifficultyRating() {
        courseReview.setDifficultyRating(1);
        assertEquals("setting course review's difficulty rating", courseReview.getDifficultyRating(), 1);
        assertNotEquals("setting course review's difficulty rating", courseReview.getDifficultyRating(), 5);

        instructorReview.setDifficultyRating(1);
        assertEquals("setting instructor review's difficulty rating", instructorReview.getDifficultyRating(), 1);
        assertNotEquals("setting instructor review's difficulty rating", instructorReview.getDifficultyRating(), 5);
    }

}