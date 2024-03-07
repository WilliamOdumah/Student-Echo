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
        // Populate based on existing data (to fake it)
        Course course1 = new Course("CS", "COMP1010" , "Introductory Computer Science 1");
        Course course2 = new Course("CS", "COMP1020", "Introduction Computer Science 2");
        Course course3 = new Course("CS", "COMP2080", "Analysis of Algorithms");
        Course course4 =new Course("CS", "COMP2140", "Data Structures and Algorithms");
        Course course5 = new Course("CS", "COMP2160", "Programming Practices");
        Course course6 =new Course("CS", "COMP3010", "Distributed Computing");
        Course course7 =new Course("CS", "COMP3020", "Human-Computer Interaction 1");
        Course course8 = new Course("CS", "COMP3350", "Software Engineering 1");
        Course course9 =new Course("CS", "COMP3380", "Databases Concepts and Usage");
        Course course10 =new Course("CS", "COMP4620", "Professional Practice in Computer Science");
        Course course11 = new Course("CS", "COMP4350", "Software Engineering 2");

        Instructor instructor1 = new Instructor("Dr.", "Gary", "Chalmers");
        Instructor instructor2 = new Instructor("Professor", "Robert", "Guderian");

        Review cReview1 = new Review(course1, "Great introductory course!",5, 2,fakeUser,2,4);
        Review cReview2 =new Review(course6, "BLOCKCHAINS!", 5, 5,fakeUser,2,4);
        Review cReview3 = new Review(course1, "Tough but rewarding.", 4, 3,fakeUser,2,4);
        Review cReview4 = new Review(course6, "Blinky blink blink", 3, 5,fakeUser,1,3);
        Review cReview5 = new Review(course2, "Well structured and informative.", 4, 4, fakeUser, 3, 1);
        Review cReview6 = new Review(course2, "Requires lots of practice.", 3, 5, fakeUser, 5, 2);
        Review cReview7 = new Review(course3, "Challenging but extremely rewarding.", 5, 5, fakeUser, 6, 0);
        Review cReview8 = new Review(course3, "Great material, tough exams.", 4, 4, fakeUser, 4, 3);
        Review cReview9 = new Review(course4, "Essential for computer science students.", 5, 3, fakeUser, 7, 1);
        Review cReview10 = new Review(course4, "Very hard to follow.", 2, 5, fakeUser, 1, 5);
        Review cReview11 = new Review(course5, "Good for practical programming skills.", 4, 3, fakeUser, 0, 0);
        Review cReview12 = new Review(course5, "Not as easy as expected.", 3, 4, fakeUser, 2, 2);
        Review cReview13 = new Review(course7, "Fascinating insights into HCI.", 5, 2, fakeUser, 9, 0);
        Review cReview14 = new Review(course7, "More theory than anticipated.", 3, 3, fakeUser, 3, 1);
        Review cReview15 = new Review(course8, "Great introduction to software engineering principles.", 4, 3, fakeUser, 10, 2);
        Review cReview16 = new Review(course8, "Project work was very insightful.", 4, 3, fakeUser, 11, 0);
        Review cReview17 = new Review(course9, "Databases made interesting and accessible.", 5, 2, fakeUser, 5, 0);
        Review cReview18 = new Review(course9, "The workload was a bit much.", 3, 4, fakeUser, 2, 3);
        Review cReview19 = new Review(course10, "Perfect blend of theory and practice.", 5, 3, fakeUser, 4, 1);
        Review cReview20 = new Review(course10, "Valuable for my professional development.", 5, 2, fakeUser, 6, 1);
        Review cReview21 = new Review(course11, "Excellent course for future software developers.", 5, 3, fakeUser, 7, 0);
        Review cReview22 = new Review(course11, "Intense but highly beneficial.", 4, 5, fakeUser, 3, 2);

        reviews.add(cReview1);
        reviews.add(cReview2);
        reviews.add(cReview3);
        reviews.add(cReview4);
        reviews.add(cReview5);
        reviews.add(cReview6);
        reviews.add(cReview7);
        reviews.add(cReview8);
        reviews.add(cReview9);
        reviews.add(cReview10);
        reviews.add(cReview11);
        reviews.add(cReview12);
        reviews.add(cReview13);
        reviews.add(cReview14);
        reviews.add(cReview15);
        reviews.add(cReview16);
        reviews.add(cReview17);
        reviews.add(cReview18);
        reviews.add(cReview19);
        reviews.add(cReview20);
        reviews.add(cReview21);
        reviews.add(cReview22);


        Review iReview1 = new Review(instructor1, "Not very helpful.",1, 3,fakeUser,10,2);
        Review iReview2 = new Review(instructor1, "Challenging exams, but fair.", 4, 4,fakeUser,1,2);
        Review iReview3 = new Review(instructor2, "He is a great prof", 5, 3,fakeUser,52,4);
        Review iReview4 = new Review(instructor2, "He gave me 100% on my 3350 project", 5, 5,fakeUser,1,2);
        reviews.add(iReview1);
        reviews.add(iReview2);
        reviews.add(iReview3);
        reviews.add(iReview4);

        Map<StudentAccount, Integer> interactions = new HashMap<>();
        interactions.put(fakeUser, 1);
        reviewInteractions.put(cReview1,interactions);
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

    private int findReviewIndexById(int reviewId) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getUid() == reviewId) {
                return i;
            }
        }
        return -1;
    }
}
