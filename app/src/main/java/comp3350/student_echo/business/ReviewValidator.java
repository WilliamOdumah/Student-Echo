package comp3350.student_echo.business;

import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.student_echo.business.Exceptions.InvalidReviewCommentException;

public class ReviewValidator {
    private static HashSet<String> flaggedWords;
    private static boolean isInitialized = false;

    private static void initialize(AssetManager assetManager) throws IOException {
        if(!isInitialized) {
            flaggedWords = new HashSet<>();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(assetManager.open("badWords.txt")));
            String word;
            while((word = reader.readLine()) != null){
                flaggedWords.add(word);
            }
            isInitialized = true;
        }
    }
    public static void validateComment(@NonNull String comment, AssetManager assetManager)
            throws InvalidReviewCommentException, IOException {
        if(!isInitialized) initialize(assetManager);

        // check for flagged words
        String[] words = comment.split(" ");
        for(String word : words) {
            if(flaggedWords.contains(word)) {
                throw new InvalidReviewCommentException( String.format("Comment contains flagged word '%s'\n",word) );
            }
        }

        // check for links
        String regex = "\\b(?:https?|ftp):\\/\\/[-A-Za-z0-9+&@#\\/=%?\\-_\\.:]*[-A-Za-z0-9+&@#\\/%=_\\.]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(comment);
        if(matcher.find()) {
            throw new InvalidReviewCommentException( String.format("Links are not allowed '%s'\n",matcher.group()) );
        }
    }
}
