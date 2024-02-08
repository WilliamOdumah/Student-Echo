package comp3350.student_echo.objects;

import java.util.Date;
import java.util.UUID;
public abstract class Review {
    private String id; // Unique identifier for each review
    private StudentAccount writtenBy;  // the user that wrote the review
    Date datePosted;
    String comment;
    int overallRating;
    int difficultyRating;
    int totalLikes;
    int totalDislikes;
    int numberOfFlagReports;

    public Review(){}
    public Review(String comment, int overallRating, int difficultyRating, StudentAccount writtenBy) {
        this.id = UUID.randomUUID().toString(); // Generate unique ID
        this.writtenBy = writtenBy;
        datePosted = new Date();
        this.comment = comment;
        this.overallRating = overallRating;
        this.difficultyRating = difficultyRating;
        totalLikes = 0;
        totalDislikes = 0;
        numberOfFlagReports = 0;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getWrittenBy() {
        return this.writtenBy.getUsername();
    }
    public String getComment() {
        return this.comment;
    }

    public int getOverallRating() {
        return this.overallRating;
    }

    public void setComment(String newComment) {
        this.comment = newComment;
    }

    public void setOverallRating(int rating) {
        this.overallRating = rating;
    }
}


