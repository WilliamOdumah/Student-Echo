package comp3350.student_echo.objects;

import java.util.Date;

public abstract class Review {
    private static final int MIN_RATING = 0;
    private static final int MAX_RATING = 5;

    Date datePosted;
    String comment;
    int overallRating;  // [0,5]
    int difficultyRating;   // [0,5]
    int totalLikes;
    int totalDislikes;
    int numberOfFlagReports;

    public Review(){}
    public Review(String comment, int overallRating, int difficultyRating) {
        datePosted = new Date();
        this.comment = comment;
        this.overallRating = (MIN_RATING <= overallRating && overallRating <= MAX_RATING) ? overallRating : 0;
        this.difficultyRating = (MIN_RATING <= difficultyRating && difficultyRating <= MAX_RATING) ? difficultyRating : 0;
        totalLikes = 0;
        totalDislikes = 0;
        numberOfFlagReports = 0;
    }

    public String getComment() {return comment;}
    public int getOverallRating() {return overallRating;}
    public int getDifficultyRating() {return difficultyRating;}

}
