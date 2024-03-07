package comp3350.student_echo.persistence.hsqldb;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.business.access.AccessAccounts;
import comp3350.student_echo.business.access.AccessCourses;
import comp3350.student_echo.business.access.AccessInstructors;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.ReviewableItem;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.ReviewPersistence;

public class ReviewPersistenceHSQLDB implements ReviewPersistence {
    private final String dbPath;
    private final AccessCourses accessCourses;
    private final AccessInstructors accessInstructors;
    private final AccessAccounts accessAccounts;

    public ReviewPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
        accessCourses = new AccessCourses();
        accessInstructors = new AccessInstructors();
        accessAccounts = new AccessAccounts();
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public void addReview(Review r) {
        try (final Connection c = connection()) {

            // Form query
            String tableName = getTableName(r);
            PreparedStatement ps = c.prepareStatement("INSERT INTO "+tableName+" VALUES(DEFAULT,?,?,?,?,?)");
            int at = 1;
            ps.setString(at++, r.getReviewableItem().getID());
            ps.setString(at++, r.getAuthorUsername());
            ps.setString(at++, r.getComment());
            ps.setInt(at++,r.getOverallRating());
            ps.setInt(at++,r.getDifficultyRating());

            // execute query to create Review in DB
            ps.executeUpdate();

            // update uid in memory (use author and comment as identifier)
            PreparedStatement ps2 = c.prepareStatement("SELECT uid FROM "+tableName+" WHERE username=? AND comment=?");
            ps2.setString(1,r.getAuthorUsername());
            ps2.setString(2,r.getComment());
            ResultSet rs = ps2.executeQuery();
            rs.next();

            r.setUID(rs.getInt("uid"));

            ps.close();
            ps2.close();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReview(Review r) {
        try (final Connection c = connection()) {
            // Form query
            String tableName = getTableName(r);
            final PreparedStatement ps = c.prepareStatement("DELETE FROM "+tableName+" r WHERE r.uid=?");
            ps.setInt(1,r.getUid());

            // execute query
            ps.executeUpdate();
            ps.close();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public List<Review> getReviewsFor(Course course) {
        List<Review> reviewList = new ArrayList<>();
        try (final Connection c = connection()) {
            // form query
            PreparedStatement ps = c.prepareStatement("SELECT * FROM course_reviews cr "+
                    "JOIN accounts acc ON acc.username=cr.username "+
                    "where cr.courseID=?");
            ps.setString(1,course.getCourseID());

            // build result into memory
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Review review = buildReviewWithCourse(rs);
                reviewList.add(review);
            }
            rs.close();
            ps.close();
            return reviewList;
        }
        catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Review> getReviewsFor(Instructor inst) {
        List<Review> reviewList = new ArrayList<>();
        try (final Connection c = connection()) {
            // form query
            final PreparedStatement ps =  c.prepareStatement("SELECT * FROM instructor_reviews ir "+
                    "JOIN accounts acc ON acc.username=ir.username "+
                    "where ir.instructorID=?");
            ps.setInt(1,inst.getInstructorID());

            // build result into memory
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Review review = buildReviewWithInstructor(rs);
                reviewList.add(review);
            }
            rs.close();
            ps.close();
            return reviewList;
        }
        catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateReview(Review r) {
        try (final Connection c = connection()) {
            // Form query
            String tableName = getTableName(r);
            final PreparedStatement ps = c.prepareStatement("UPDATE "+tableName+" "+
                    "SET comment=?,overall_rating=?,difficulty_rating=? "+
                    "WHERE uid=?");
            ps.setString(1,r.getComment());
            ps.setInt(2,r.getOverallRating());
            ps.setInt(3,r.getDifficultyRating());
            ps.setInt(4,r.getUid());

            // execute query
            ps.executeUpdate();
            ps.close();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addInteraction(Review r, StudentAccount sa, int state) {
        try (final Connection c = connection()){
            // Form query
            String tableName = getTableName(r, sa);
            final PreparedStatement ps = c.prepareStatement("INSERT INTO "+tableName+" VALUES(?,?)");
            ps.setString(1, sa.getUsername());    // username
            ps.setInt(2, r.getUid());             // review id
            ps.setInt(3, state);                  // state

            // execute query
            ps.executeUpdate();
            ps.close();

            // passing means like successfully added
            return true;
        } catch(SQLIntegrityConstraintViolationException e) {
            // this exception means like already added
            return false;
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return false;
    }

    public boolean addOrUpdateInteraction(Review r, StudentAccount sa, int newState) {
        System.out.println("ADDING OR UPDATING INTERACTION");
        try (final Connection c = connection()) {
            Integer currentState = getInteractionState(r, sa);
            String tableName =  getTableName(r, sa);
            String query;
            if (currentState == null) { // New interaction
                System.out.println("ADDING INTERACTION");
                query = "INSERT INTO " + tableName + " (USERNAME, REVIEW_ID, STATE) VALUES (?, ?, ?)";
                System.out.println("INTERACTION ADDED FOR "+sa.getUsername());
            } else { // Update existing interaction
                System.out.println("UPDATING INTERACTION");
                query = "UPDATE " + tableName + " SET STATE = ? WHERE USERNAME = ? AND REVIEW_ID = ?";
            }
            final PreparedStatement ps = c.prepareStatement(query);
            if (currentState == null) {
                ps.setString(1, sa.getUsername());
                ps.setInt(2, r.getUid());
                ps.setInt(3, newState);
            } else {
                ps.setInt(1, newState);
                ps.setString(2, sa.getUsername());
                ps.setInt(3, r.getUid());
            }
            // Execute the update or insert
            ps.executeUpdate();
            System.out.println("INTERACTION ADDED FOR "+sa.getUsername()+" WITH STATE= " +getInteractionState(r,sa));

            ps.close();
            return true;
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return false;
    }


    public Integer getInteractionState(Review r, StudentAccount sa) {
        System.out.println("GETTING STATE.....");
        Integer state = null;
        try (final Connection c = connection()) {
            String tableName = getTableName(r, sa);
            final PreparedStatement ps = c.prepareStatement("SELECT state FROM " + tableName + " WHERE USERNAME = ? AND REVIEW_ID = ?");
            ps.setString(1, sa.getUsername());
            ps.setInt(2, r.getUid());

            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                state = rs.getInt("state");
            }
            rs.close();
            ps.close();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        System.out.println("STATE IS = "+state);
        return state;
    }

    @Override
    public void updateLikeCount(Review r){
        try (final Connection c = connection()){
            String tableName = getTableName(r);
            PreparedStatement ps = c.prepareStatement("UPDATE " + tableName + " SET LIKE_COUNT = ? WHERE UID = ?");

            ps.setInt(1, r.getLikes());
            ps.setInt(2,r.getUid());
            ps.executeUpdate();
            ps.close();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDislikeCount(Review r) {
        try (final Connection c = connection()){
            String tableName = getTableName(r);
            PreparedStatement ps = c.prepareStatement("UPDATE " + tableName + " SET DISLIKE_COUNT = ? WHERE UID = ?");

            ps.setInt(1, r.getDislikes());
            ps.setInt(2,r.getUid());
            ps.executeUpdate();
            ps.close();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }


    private Review buildReviewWithCourse(final ResultSet rs) throws SQLException {
        final int reviewID = rs.getInt("uid");
        final String courseID = rs.getString("courseID");
        final String username = rs.getString("username");
        final String comment = rs.getString("comment");
        final int overallRating = rs.getInt("overall_rating");
        final int difficultyRating = rs.getInt("difficulty_rating");

        Course course = accessCourses.getCourse(courseID);
        StudentAccount author = accessAccounts.getAccount(username);
        return new Review(reviewID, course, comment, overallRating, difficultyRating, author);
    }
    private Review buildReviewWithInstructor(final ResultSet rs) throws SQLException {
        final int reviewID = rs.getInt("uid");
        final int instructorID = rs.getInt("instructorID");
        final String username = rs.getString("username");
        final String comment = rs.getString("comment");
        final int overallRating = rs.getInt("overall_rating");
        final int difficultyRating = rs.getInt("difficulty_rating");

        Instructor instructor = accessInstructors.getInstructor(instructorID);
        StudentAccount author = accessAccounts.getAccount(username);
        return new Review(reviewID, instructor, comment, overallRating, difficultyRating, author);
    }
    private String getTableName(Review r) {
        ReviewableItem item = r.getReviewableItem();
        if(item instanceof Course) return "course_reviews";
        if(item instanceof Instructor) return "instructor_reviews";
        return null;
    }
    private String getTableName(Review r, StudentAccount sa) {
        ReviewableItem item = r.getReviewableItem();
        if(item instanceof Course) return "interactions_course_review";
        if(item instanceof Instructor) return "interactions_instructor_review";
        return null;
    }
}
