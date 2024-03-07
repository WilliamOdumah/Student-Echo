package comp3350.student_echo.tests.business;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.res.AssetManager;

import comp3350.student_echo.business.ReviewValidator;

import comp3350.student_echo.business.Exceptions.InvalidReviewCommentException;

public class ReviewValidatorTest {

    private static final String FLAGGED_WORDS_FILE_CONTENT = "word1\nword2\nword3";

    @Mock
    private AssetManager mockAssetManager;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test()
    public void testValidateCommentWithFlaggedWord() throws IOException {
        // Mock the input stream from the asset manager
        ByteArrayInputStream inputStream = new ByteArrayInputStream(FLAGGED_WORDS_FILE_CONTENT.getBytes());
        when(mockAssetManager.open("badWords.txt")).thenReturn(inputStream);

        // Create a comment with a flagged word
        String comment = "This is a word1 comment";

        // Verify exception
        try {
            ReviewValidator.validateComment(comment, mockAssetManager);
            fail("Expected InvalidReviewCommentException");
        } catch (InvalidReviewCommentException e) {
            System.out.println("THIS IS THE MESSAGE: " + e.getMessage());
            assertTrue(e.getMessage().contains("word1"));
        }
    }

    @Test
    public void testValidateCommentWithLink() throws IOException {
        // Mock the input stream from the asset manager
        ByteArrayInputStream inputStream = new ByteArrayInputStream(FLAGGED_WORDS_FILE_CONTENT.getBytes());
        when(mockAssetManager.open("badWords.txt")).thenReturn(inputStream);

        // Create a comment with a link
        String comment = "Check out this link: https://example.com";

        //  verify exception
        try {
            ReviewValidator.validateComment(comment, mockAssetManager);
            fail("Expected InvalidReviewCommentException");
        } catch (InvalidReviewCommentException e) {
            assertTrue(e.getMessage().contains("https://example.com"));
        }
    }

    @Test
    public void testValidateCommentWithValid() throws IOException {
        // Mock the input stream from the asset manager
        ByteArrayInputStream inputStream = new ByteArrayInputStream(FLAGGED_WORDS_FILE_CONTENT.getBytes());
        when(mockAssetManager.open("badWords.txt")).thenReturn(inputStream);

        String comment = "There are no bad words here";

        // no exception
        ReviewValidator.validateComment(comment, mockAssetManager);
    }

}
