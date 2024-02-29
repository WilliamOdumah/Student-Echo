package comp3350.student_echo.objects;

import java.io.Serializable;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;
public abstract class Review implements Serializable {
    private UUID uid;
    private StudentAccount author;
    private String comment;
    private int overallRating;
    private int difficultyRating;

    private int likes;
    private int dislikes;

    private Map<String, Boolean> userLikesMap;
    private Map<String, Boolean> userDislikesMap;
    public Review(){}
    public Review(String comment, int overallRating, int difficultyRating, StudentAccount author) {
        this.uid = UUID.randomUUID();
        this.author = author;
        this.comment = comment;
        this.overallRating = overallRating;
        this.difficultyRating = difficultyRating;
        this.userLikesMap = new HashMap<>();
        this.userDislikesMap = new HashMap<>();
    }

    // Getters
    public UUID getUid() {
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
    public int getLikes() {
        return likes;
    }
    public int getDislikes() {
        return dislikes;
    }
    public Map<String,Boolean> getUserLikesMap(){
        return this.userLikesMap;
    }
    public Map<String,Boolean> getUserDislikesMap(){
        return this.userDislikesMap;
    }



    // Setters
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
        this.userLikesMap.put(userId,liked);
    }
    public void removeUserLike(String userId) {
        userLikesMap.remove(userId);
    }
    public void setUserDisliked(String userId, Boolean liked){
        this.userDislikesMap.put(userId,liked);
    }
    public void removeUserDislike(String userId) {
        userDislikesMap.remove(userId);
    }
}


