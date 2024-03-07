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
    private int likes;
    private int dislikes;

    public Review(){}
    // constructor for new Review creation
    public Review(ReviewableItem item, String comment, int overallRating, int difficultyRating, StudentAccount author, int likes, int dislikes) {
        this(-1, item, comment, overallRating, difficultyRating, author, likes, dislikes);
    }

    // constructor for building Review from Persistence
    public Review(int uid, ReviewableItem item, String comment, int overallRating, int difficultyRating,
                  StudentAccount author, int likes, int dislikes) {
        this.uid = uid;
        this.item = item;
        this.author = author;
        this.comment = comment;
        this.overallRating = overallRating;
        this.difficultyRating = difficultyRating;
        this.likes =likes;
        this.dislikes=dislikes;
    }

    public boolean equals(Review r){
        return  r.getUid()==this.uid;
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
    public int getLikes() {
        return this.likes;
    }
    public int getDislikes() {
        return this.dislikes;
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
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
    public void setUserLiked(String userId, Boolean liked){

    }
    public void removeUserLike(String userId) {

    }
    public void setUserDisliked(String userId, Boolean liked){

    }
    public void removeUserDislike(String userId) {

    }
}


