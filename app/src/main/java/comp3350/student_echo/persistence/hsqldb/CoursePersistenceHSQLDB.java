package comp3350.student_echo.persistence.hsqldb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.persistence.CoursePersistence;

public class CoursePersistenceHSQLDB implements CoursePersistence {
    private final String dbPath;

    public CoursePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
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

}
