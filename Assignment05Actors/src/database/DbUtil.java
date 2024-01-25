package database;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DbUtil {
    private static final String URL = "jdbc:mysql://localhost:3307/sakila";
    private static final String USR = "root";
    private static final String PWD = "Hsetpaing1500";

    public static CachedRowSet createRowSet() throws SQLException{
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet rowset = factory.createCachedRowSet();
        setConnInfo(rowset);
        return rowset;
    }

    public static void setConnInfo(RowSet rs) throws SQLException {
        rs.setUrl(URL);
        rs.setUsername(USR);
        rs.setPassword(PWD);
    }

    // params are values in order to set in prepared statement positional ? ? ?
    public static int runUpdateSql(String sql, Object[] params) throws SQLException{
        try (Connection conn = DriverManager.getConnection(URL, USR, PWD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set params
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            // Execute the update
            int numRows = stmt.executeUpdate();
            return numRows;
        } catch (SQLException e) {
            throw e;
        }
    }

}

