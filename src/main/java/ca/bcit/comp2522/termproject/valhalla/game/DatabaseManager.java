package ca.bcit.comp2522.termproject.valhalla.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {
    private DatabaseManager() { }

    /**
     * Drives the example.
     *
     * @param args unused
     * @throws SQLException if a connection cannot be made.
     * @throws ClassNotFoundException if the MySQL ConnectorJ JDBC JAR cannot be found, i.e., is not on classpath
     */
    public static void main(final String[] args) throws SQLException, ClassNotFoundException {

        // We register the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // We identify the driver, the rdbms, the host, the port, and the schema name
        final String url = "jdbc:mysql://localhost:3306/comp2522";

        // We need to send a user and a password when we try to connect!
        final Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "eggonomics");

        // We establish a connection...
        final Connection connection = DriverManager.getConnection(url, connectionProperties);
        if (connection != null) {
            System.out.println("Successfully connected to MySQL database test");
        }

        // Create a statement to send on the connection...
        assert connection != null;
        Statement stmt = connection.createStatement();

        // Execute the statement and receive the result...
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        // And then display the result!
        System.out.println("user_id\t\tpassword");
        while (rs.next()) {
            String userID = rs.getString("user_id");
            String password = rs.getString("password");
            System.out.println(userID + "\t\t" + password);
        }
    }
}
