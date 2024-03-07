package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.ReviewPersistence;


public class ReviewPersistenceStub implements ReviewPersistence {
    private final List<Review> reviews;
    private final Map<Review, Map<StudentAccount, Integer>> reviewInteractions;

    public ReviewPersistenceStub() {
        reviews = new ArrayList<>();
        reviewInteractions = new HashMap<>();
        populateStub();
    }

    private void populateStub() {
        StudentAccount fakeUser = new StudentAccount("fake","fakepassword","fake@myumanitoba.ca");
        Course course1 = new Course("Computer Science", "COMP1010" , "Introductory Computer Science");
        Course course2 = new Course("CS", "COMP3010", "Distributed Computing");

        Instructor instructor1 = new Instructor("Dr.", "Gary", "Chalmers");
        Instructor instructor2 = new Instructor("Professor", "Mary", "Bailey");

        reviews.add(new Review(course1, "Great introductory course!", 5, 2,fakeUser));
        reviews.add(new Review(course1, "Tough but rewarding.", 4, 3,fakeUser));
        Review review1 =new Review(course2, "BLOCKCHAINS!", 5, 5,fakeUser);
        reviews.add(review1);
        reviews.add(new Review(course2, "Blinky blink blink", 3, 5,fakeUser));

        Map<StudentAccount, Integer> interactions = new HashMap<>();
        interactions.put(fakeUser, 1);
        reviewInteractions.put(review1,interactions);

        reviews.add(new Review(instructor1, "Very knowledgeable and helpful.", 5, 3,fakeUser));
        reviews.add(new Review(instructor1, "Challenging exams, but fair.", 4, 4,fakeUser));
        reviews.add(new Review(instructor2, "She is a great prof", 5, 3,fakeUser));
        reviews.add(new Review(instructor2, "She gave me a 0 on my the assignment", 1, 5,fakeUser));
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
    public boolean addOrUpdateInteraction(Review r, StudentAccount sa, int newState) {
        reviewInteractions.putIfAbsent(r, new HashMap<>());
        Map<StudentAccount, Integer> interactions = reviewInteractions.get(r);
        interactions.put(sa, newState); // This will add or update the user's interaction
        return true; // Stub assumes operation always succeeds
    }

    @Override
    public Integer getInteractionState(Review r, StudentAccount sa) {
        Map<StudentAccount, Integer> interactions = reviewInteractions.get(r);
        if (interactions != null) {
            return interactions.getOrDefault(sa, 0); // Defaults to 0 if user has not interacted
        }
        return 0; // Return 0 if there are no interactions for this review
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
