package comp3350.student_echo.objects;

import java.io.Serializable;
public abstract class Review implements Serializable {
    private int uid;
    private StudentAccount author;
    private String comment;
    private int overallRating;
    private int difficultyRating;

    public Review(String comment, int overallRating, int difficultyRating, StudentAccount author) {
        this(-1, comment, overallRating, difficultyRating, author);
    }

    public Review(int uid, String comment, int overallRating, int difficultyRating, StudentAccount author) {
        this.uid = uid;
        this.author = author;
        this.comment = comment;
        this.overallRating = overallRating;
        this.difficultyRating = difficultyRating;
    }

    // Getters
    public int getUid() {
        return uid;
    }
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


