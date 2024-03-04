package comp3350.student_echo.persistence.hsqldb;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.business.AccessAccounts;
import comp3350.student_echo.business.AccessCourses;
import comp3350.student_echo.business.AccessInstructors;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
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

    private CourseReview fromResultSetCR(final ResultSet rs) throws SQLException {
        final int reviewID = rs.getInt("uid");
        final String courseID = rs.getString("courseID");
        final String username = rs.getString("username");
        final String comment = rs.getString("comment");
        final int overallRating = rs.getInt("overall_rating");
        final int difficultyRating = rs.getInt("difficulty_rating");

        Course course = accessCourses.getCourse(courseID);
        StudentAccount sa = accessAccounts.getAccount(username);
        return new CourseReview(reviewID, course, comment, overallRating, difficultyRating, sa);
    }
    private InstructorReview fromResultSetIR(final ResultSet rs) throws SQLException {
        final int reviewID = rs.getInt("uid");
        final int instructorID = rs.getInt("instructorID");
        final String username = rs.getString("username");
        final String comment = rs.getString("comment");
        final int overallRating = rs.getInt("overall_rating");
        final int difficultyRating = rs.getInt("difficulty_rating");

        Instructor instructor = accessInstructors.getInstructor(instructorID);
        StudentAccount sa = accessAccounts.getAccount(username);
        return new InstructorReview(reviewID, instructor, comment, overallRating, difficultyRating, sa);
    }

    @Override
    public void addReview(Review r) {
        try (final Connection c = connection()) {
            // Form query
            String tableName = (r instanceof CourseReview) ? "course_reviews" : "instructor_reviews";
            final PreparedStatement ps = c.prepareStatement("INSERT INTO "+tableName+" VALUES(?,?,?,?,?,?)");
            int at = 1;
            ps.setInt(at++, r.getUid());
            if(r instanceof CourseReview) ps.setString(at++,((CourseReview)r).getCourse().getCourseID());
            else ps.setInt(at++,((InstructorReview)r).getInstructor().getInstructorID());
            ps.setString(at++, r.getAuthorUsername());
            ps.setString(at++, r.getComment());
            ps.setInt(at++,r.getOverallRating());
            ps.setInt(at++,r.getDifficultyRating());

            // execute query
            ps.executeUpdate();
            ps.close();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReview(Review r) {
        try (final Connection c = connection()) {
            // Form query
            String tableName = (r instanceof CourseReview) ? "course_reviews" : "instructor_reviews";
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
            PreparedStatement ps = c.prepareStatement("SELECT * FROM course_reviews cr "+
                    "JOIN accounts acc ON acc.username=cr.username "+
                    "where cr.courseID=?");
            ps.setString(1,course.getCourseID());
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final CourseReview cr = fromResultSetCR(rs);
                reviewList.add(cr);
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
            final PreparedStatement ps =  c.prepareStatement("SELECT * FROM instructor_reviews ir "+
                    "JOIN accounts acc ON acc.username=ir.username "+
                    "where ir.instructorID=?");
            ps.setInt(1,inst.getInstructorID());
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final InstructorReview ir = fromResultSetIR(rs);
                reviewList.add(ir);
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
            String tableName = (r instanceof CourseReview) ? "course_reviews" : "instructor_reviews";
            final PreparedStatement ps = c.prepareStatement("UPDATE "+tableName+" "+
                    "SET comment=?,overall_rating=?,difficulty_rating=? "+
                    "WHERE uid=?");
            ps.setString(1,r.getComment());
            ps.setInt(2,r.getOverallRating());
            ps.setInt(3,r.getDifficultyRating());
            System.out.println("THIS IS THE reviewID we want=" + r.getUid());
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
}
