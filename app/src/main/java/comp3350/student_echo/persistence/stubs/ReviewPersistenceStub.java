package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.ReviewPersistence;


public class ReviewPersistenceStub implements ReviewPersistence {
    private final List<Review> reviews;
    private final Map<Review, Set<StudentAccount>> reviewLikedBy;

    public ReviewPersistenceStub() {
        reviews = new ArrayList<>();
        reviewLikedBy = new HashMap<>();
        populateStub();
    }

    private void populateStub() {
        StudentAccount fakeUser = new StudentAccount("fake","fakepassword","fake@myumanitoba.ca");
        Course course1 = new Course("Computer Science", "COMP1010" , "Introductory Computer Science");
        Course course2 = new Course("CS", "COMP3010", "Distributed Computing");

        Instructor instructor1 = new Instructor("Dr.", "Gary", "Chalmers");
        Instructor instructor2 = new Instructor("Professor", "Mary", "Bailey");

        reviews.add(new Review(course1, "Great introductory course!", 5, 2,fakeUser,0,0));
        reviews.add(new Review(course1, "Tough but rewarding.", 4, 3,fakeUser,0,0));
        reviews.add(new Review(course2, "BLOCKCHAINS!", 5, 5,fakeUser,0,0));
        reviews.add(new Review(course2, "Blinky blink blink", 3, 5,fakeUser,0,0));

        reviews.add(new Review(instructor1, "Very knowledgeable and helpful.", 5, 3,fakeUser,0,0));
        reviews.add(new Review(instructor1, "Challenging exams, but fair.", 4, 4,fakeUser,0,0));
        reviews.add(new Review(instructor2, "She is a great prof", 5, 3,fakeUser,0,0));
        reviews.add(new Review(instructor2, "She gave me a 0 on my the assignment", 1, 5,fakeUser,0,0));
    }

    @Override
    public void addReview(Review r) {
        reviews.add(r);
    }

    @Override
    public void deleteReview(Review r) {
        reviews.remove(r);
    }

    @Override
    public List<Review> getReviewsFor(Course c) {
        List<Review> result = new ArrayList<>();
        for (Review review : reviews) {
            if(review.getReviewableItem() instanceof Course) {
                Course courseOfReview = (Course) review.getReviewableItem();
                if (courseOfReview.equals(c)) {
                    result.add(review);
                }
            }
        }
        return result;
    }

    @Override
    public List<Review> getReviewsFor(Instructor inst) {
        List<Review> result = new ArrayList<>();
        for (Review review : reviews) {
            if(review.getReviewableItem() instanceof Instructor) {
                Instructor instructorOfReview = (Instructor) review.getReviewableItem();
                if(instructorOfReview.equals(inst)) {
                    result.add(review);
                }
            }
        }
        return result;
    }

    @Override
    public boolean updateReview(Review r){
        int index = findReviewIndexById(r.getUid());
        if (index != -1) {
            reviews.set(index,r);
            return true;
        }
        return false;
    }

    @Override
    public boolean addInteraction(Review r, StudentAccount sa, int state) {
        reviewLikedBy.putIfAbsent(r, new HashSet<>());
        Set<StudentAccount> likeSet = reviewLikedBy.get(r);
        return likeSet.add(sa);
    }

    @Override
    public boolean addOrUpdateInteraction(Review r, StudentAccount sa, int newState) {
        return false;
    }

    @Override
    public Integer getInteractionState(Review r, StudentAccount sa) {
        return null;
    }

    @Override
    public void updateLikeCount(Review r) {

    }

    @Override
    public void updateDislikeCount(Review r) {

    }

    private int findReviewIndexById(int reviewId) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getUid() == reviewId) {
                return i;
            }
        }
        return -1;
    }
}
