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


    public Statement addUserToTable(Connection conn,String log, String pass) throws SQLException{
        try {

            String SQL = "INSERT libraryUser(login,password) VALUES ('log', 'pass')";
            statement = conn.createStatement();
            statement.executeUpdate(SQL);

        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
            if(statement!=null){
                statement.close();
            }
        }

        return statement;
    }


    public Statement createTable(Connection conn) throws SQLException {
        try {
            String SQL ="CREATE TABLE IF NOT EXISTS LibraryUser"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"login INTEGER NOT NULL UNIQUE,"
                    + "password VARCHAR (30) NOT NULL UNIQUE)";
            statement = conn.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryReader"
                    + "(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    + "login INTEGER,"
                    + "password VARCHAR (30),"
                    + "nameReader VARCHAR (30),"
                    + "surname VARCHAR (30),"
                    + "patronymic VARCHAR (30),"
//                    + "passwordID VARCHAR (30),"
                    + "phone VARCHAR (30),"
                    + "birthDay DATE,"
//                    + "IDIDID INTEGER,"
//                    + "CONSTRAINT FK_login FOREIGN KEY (login)" +
//                    "REFERENCES LibraryUser(login))";
//                    + "FOREIGN KEY(password) REFERENCES LibraryUser(password),"
                    + "FOREIGN KEY(login) REFERENCES LibraryUser(login))";


            statement = conn.createStatement();
            statement.executeUpdate(SQL);



        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
            if(statement!=null){
                statement.close();
            }
        }


        return statement;
    }

    public void dropTable(Connection conn) throws SQLException {
        try{
            String SQL="DROP TABLE LibraryUser";
            statement= conn.createStatement();
            statement.executeUpdate(SQL);
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public void insertUser(User user,Connection conn){
        String SQL = "INSERT INTO LibraryUser(login,password) "
                + "VALUES(?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}