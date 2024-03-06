package comp3350.student_echo.persistence.hsqldb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;

import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.persistence.CoursePersistence;

public class CoursePersistenceHSQLDB implements CoursePersistence {
    private final String dbPath;

    public CoursePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true;sql.ignore_case=true", "SA", "");
    }

    private Course fromResultSet(final ResultSet rs) throws SQLException {
        final String courseID = rs.getString("courseID");
        final String courseName = rs.getString("courseName");
        final String department = rs.getString("department");
        return new Course(department, courseID, courseName);
    }

    @Override
    public List<Course> getCourseSequential(){
        final List<Course> courses = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM courses");
            while (rs.next()) {
                final Course course = fromResultSet(rs);
                courses.add(course);
            }
            rs.close();
            st.close();
            return courses;
        }
        catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Course getCourse(String courseID) {
        try (final Connection c = connection()) {
            // form query
            final PreparedStatement ps = c.prepareStatement("SELECT * FROM courses WHERE courses.courseid=?");
            ps.setString(1, courseID);

            // execute
            final ResultSet rs = ps.executeQuery();
            rs.next();  // point to data

            // build result
            final Course course = fromResultSet(rs);
            rs.close();
            ps.close();
            return course;
        }
        catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Course getCourseOnName(String courseName) {
        try (final Connection c = connection()) {
            // form query
            final PreparedStatement ps = c.prepareStatement("SELECT * FROM courses WHERE LOWER(courses.coursename)=LOWER(?)");
            ps.setString(1, courseName);

            // execute
            final ResultSet rs = ps.executeQuery();
            rs.next();  // point to data

            // build result
            final Course course = fromResultSet(rs);
            rs.close();
            ps.close();
            return course;
        }
        catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }
}
