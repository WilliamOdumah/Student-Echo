package comp3350.student_echo.persistence.hsqldb;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.Department;
import comp3350.student_echo.persistence.DepartmentPersistence;

import java.sql.DriverManager;


public class DepartmentPersistenceHSQLDB implements DepartmentPersistence {

    private final String dbPath;

    public DepartmentPersistenceHSQLDB(String dbPathName) {
        this.dbPath = dbPathName;
    }
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Department fromResultSet(ResultSet rs) throws SQLException {
        final String departmentName = rs.getString("departmentName");
        return new Department(departmentName);
    }
    @Override
    public List<Department> getDepartmentSequential() {
        final List<Department> departments = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM DEPARTMENTS");
            while (rs.next()) {
                final Department department = fromResultSet(rs);
                departments.add(department);
            }
            rs.close();
            st.close();

            return departments;
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Department getDepartment(String departmentName) {
        try (final Connection c = connection()) {
            // form query
            final PreparedStatement ps = c.prepareStatement("SELECT * FROM DEPARTMENTS WHERE departments.departmentname=?");
            ps.setString(1, departmentName);

            // execute
            final ResultSet rs = ps.executeQuery();
            rs.next();  // point to data

            // build result
            final Department department = fromResultSet(rs);
            rs.close();
            ps.close();
            return department;
        }
        catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }
}
