import java.sql.*;

public class Database {
    String userName = "root";
    String password = "1234";

    String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql";
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    Statement statement = null;

    public Connection databaseConnection() throws SQLException {
        Connection connection = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, userName, password);

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            if (connection != null) {
                connection.close();
            }
        }
        return connection;
    }
}