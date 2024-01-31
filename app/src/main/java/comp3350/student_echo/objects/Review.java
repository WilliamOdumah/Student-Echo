package comp3350.student_echo.objects;

import java.util.Date;

public abstract class Review {
    Date datePosted;
    String comment;
    int overallRating;
    int difficultyRating;
    int totalLikes;
    int totalDislikes;
    int numberOfFlagReports;

    public Review(){}
    public Review(String comment, int overallRating, int difficultyRating) {
        datePosted = new Date();
        this.comment = comment;
        this.overallRating = overallRating;
        this.difficultyRating = difficultyRating;
        totalLikes = 0;
        totalDislikes = 0;
        numberOfFlagReports = 0;
    }

}
