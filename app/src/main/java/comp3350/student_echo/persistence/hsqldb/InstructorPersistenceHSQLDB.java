package comp3350.student_echo.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import android.util.Log; // TODO: IS IT OK TO HAVE ANDROID LOG HERE??

import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.persistence.InstructorPersistence;

public class InstructorPersistenceHSQLDB implements InstructorPersistence {
    private final String dbPath;

    public InstructorPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Instructor fromResultSet(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        final String title = rs.getString("title");
        final String firstName = rs.getString("firstName");
        final String lastName = rs.getString("lastName");
        return new Instructor(id, title, firstName, lastName);
    }

    @Override
    public List<Instructor> getInstructorSequential(){
        final List<Instructor> instructors = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM instructors");
            while (rs.next()) {
                final Instructor instructor = fromResultSet(rs);
                instructors.add(instructor);
            }
            rs.close();
            st.close();
            return instructors;
        }
        catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Instructor getInstructor(int instructorID) {
        try (final Connection c = connection()) {
            // form query
            final PreparedStatement ps = c.prepareStatement("SELECT * FROM instructors WHERE instructors.id=?");
            ps.setInt(1, instructorID);

            // execute
            final ResultSet rs = ps.executeQuery();
            rs.next();  // point to data

            // build result
            final Instructor instructor = fromResultSet(rs);
            rs.close();
            ps.close();
            return instructor;
        }
        catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }
}
