package comp3350.student_echo.objects;

import java.io.Serializable;

import comp3350.student_echo.objects.reviewableItems.ReviewableItem;

public class Review implements Serializable {
    private ReviewableItem item;
    private int uid;
    private StudentAccount author;
    private String comment;
    private int overallRating;
    private int difficultyRating;

    // constructor for new Review creation
    public Review(ReviewableItem item, String comment, int overallRating, int difficultyRating, StudentAccount author) {
        this(-1, item, comment, overallRating, difficultyRating, author);
    }

    // constructor for building Review from Persistence
    public Review(int uid, ReviewableItem item, String comment, int overallRating, int difficultyRating,
                  StudentAccount author) {
        this.uid = uid;
        this.item = item;
        this.author = author;
        this.comment = comment;
        this.overallRating = overallRating;
        this.difficultyRating = difficultyRating;
    }

    // Getters
    public int getUid() {
        return uid;
    }
    public ReviewableItem getReviewableItem(){return item;}
    public String getAuthorUsername() {
        return this.author.getUsername();
    }
    public String getComment() {
        return this.comment;
    }
    public int getOverallRating() {
        return this.overallRating;
    }
    public int getDifficultyRating(){
        return this.difficultyRating;
    }

    // Setters
    public void setUID(int id) {this.uid = id;}
    public void setComment(String newComment) {
        this.comment = newComment;
    }
    public void setOverallRating(int rating) {
        this.overallRating = rating;
    }
    public void setDifficultyRating(int rating) {
        this.difficultyRating = rating;
    }

}


