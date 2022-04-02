package ca.bcit.comp2522.termproject.valhalla.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * A DatabaseManager class to manage the Valhalla database.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public class DatabaseManager {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int PORT = 3306;
    private static final String URL = "jdbc:mysql://localhost:" + PORT + "/";
    private static final String DATABASE_NAME = "comp2522";

    private static final String CONNECTION_USERNAME = "root";
    private static final String CONNECTION_PASSWORD = "eggonomics";

    private final Connection connection;

    /**
     * Creates a DatabaseManager object.
     * @throws ClassNotFoundException thrown if the JDBC Driver cannot be found
     * @throws SQLException thrown if connection properties given were incorrect
     */
    public DatabaseManager()
            throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", CONNECTION_USERNAME);
        connectionProperties.put("password", CONNECTION_PASSWORD);

        connection = DriverManager.getConnection(URL + DATABASE_NAME, connectionProperties);
    }

    /*
     * Sends a query to the Valhalla database.
     * @return the result of the query as a ResultSet
     * @throws
     */
    private ResultSet query(final String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

//    public ResultSet search(final String table, final String username) {
//        try {
//            return query("SELECT * FROM " + table);
//        } catch (SQLException e) {
//            return null;
//        }
//    }

//    public void add(final String table, final HashMap<String, String> values) {
//        // catches a value mismatch, making pk null, domain constraint violation
//    }

//    public void delete(final String table, final String username) {
//        // catches deleting null, referential integrity violation
//    }

//    public void update(final String table, final String column, final String newValue) {
//        // catches a domain constraint violation or an entity integrity violation
//    }

    /**
     * Drives sample of DatabaseManager.
     * @param args unused
     */
    public static void main(final String[] args) {
        DatabaseManager db;
        ResultSet rs;
        try {
            db = new DatabaseManager();
            rs = db.query("SELECT * FROM USERS");

            System.out.println("user_id\t\tpassword");
            while (rs.next()) {
                String userID = rs.getString("user_id");
                String password = rs.getString("password");
                System.out.println(userID + "\t\t" + password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
