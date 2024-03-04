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

import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.AccountPersistence;

public class AccountPersistenceHSQLDB implements AccountPersistence {
    private final String dbPath;

    public AccountPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private StudentAccount fromResultSet(final ResultSet rs) throws SQLException {
        final String username = rs.getString("username");
        final String password = rs.getString("password");
        final String email = rs.getString("email");
        return new StudentAccount(username, password, email);
    }

    @Override
    public List<StudentAccount> getAccountSequential(){
        final List<StudentAccount> accounts = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM accounts");
            while (rs.next()) {
                final StudentAccount account = fromResultSet(rs);
                accounts.add(account);
            }
            rs.close();
            st.close();
            return accounts;
        }
        catch (final SQLException e) {
            System.out.println("FAILED TO GET ALL ACCOUNTS");
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addAccount(StudentAccount account) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO accounts VALUES(?,?,?)");

            st.setString(1, account.getUsername());
            st.setString(2, account.getPassword());
            st.setString(3, account.getEmail());

            st.executeUpdate();
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    // TODO
    @Override
    public void updateAccount(StudentAccount currentStudent) {

    }
}