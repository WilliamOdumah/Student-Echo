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
        final int uid = rs.getInt("uid");
        final String courseID = rs.getString("courseID");
        final String username = rs.getString("username");
        final String comment = rs.getString("comment");
        final int overallRating = rs.getInt("overall_rating");
        final int difficultyRating = rs.getInt("difficulty_rating");

        Course course = accessCourses.getCourse(courseID);
        StudentAccount sa = accessAccounts.getAccount(username);
        return new CourseReview(course, comment, overallRating, difficultyRating, sa);
    }
    private InstructorReview fromResultSetIR(final ResultSet rs) throws SQLException {
        final int reviewUID = rs.getInt("uid");
        final int instructorID = rs.getInt("instructorID");
        final String username = rs.getString("username");
        final String comment = rs.getString("comment");
        final int overallRating = rs.getInt("overall_rating");
        final int difficultyRating = rs.getInt("difficulty_rating");

        Instructor instructor = accessInstructors.getInstructor(instructorID);
        StudentAccount sa = accessAccounts.getAccount(username);
        return new InstructorReview(instructor, comment, overallRating, difficultyRating, sa);
    }

    @Override
    public void addReview(Review r) {
        try (final Connection c = connection()) {
            // Form query
            String tableName = (r instanceof CourseReview) ? "course_reviews" : "instructor_reviews";
            final PreparedStatement st = c.prepareStatement("INSERT INTO "+tableName+" VALUES(?,?,?,?,?,?)");
            int at = 1;
            st.setInt(at++, r.getUid());
            if(r instanceof CourseReview) st.setString(at++,((CourseReview)r).getCourse().getCourseID());
            else st.setInt(at++,((InstructorReview)r).getInstructor().getInstructorID());
            st.setString(at++, r.getAuthorUsername());
            st.setString(at++, r.getComment());
            st.setInt(at++,r.getOverallRating());
            st.setInt(at++,r.getDifficultyRating());

            // execute query
            st.executeUpdate();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    // TODO
    @Override
    public void deleteReview(Review r) {

    }

    @Override
    public List<CourseReview> getReviewsFor(Course course) {
        List<CourseReview> reviewList = new ArrayList<>();
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
    public List<InstructorReview> getReviewsFor(Instructor inst) {
        List<InstructorReview> reviewList = new ArrayList<>();
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

    // TODO
    @Override
    public boolean updateReview(Review updatedReview) {
        return false;
    }
}
