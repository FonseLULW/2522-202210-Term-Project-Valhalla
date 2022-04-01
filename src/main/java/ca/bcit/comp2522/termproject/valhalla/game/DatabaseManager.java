package ca.bcit.comp2522.termproject.valhalla.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int PORT = 3306;
    private static final String URL = "jdbc:mysql://localhost:" + PORT + "/";

    private final Connection connection;

    public DatabaseManager(final String database, final String username, final String password)
            throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", username);
        connectionProperties.put("password", password);

        connection = DriverManager.getConnection(URL + database, connectionProperties);
    }

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

    public static void main(final String[] args) {
        DatabaseManager db;
        ResultSet rs;
        try {
            db = new DatabaseManager("comp2522", "root", "eggonomics");
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
