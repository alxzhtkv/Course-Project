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

           SQL ="CREATE TABLE IF NOT EXISTS LibraryAdmin"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"login INTEGER NOT NULL UNIQUE,"
                    + "password VARCHAR (30) NOT NULL UNIQUE)";
            statement = conn.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryReader"
//                    + "(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
////                    + "login INTEGER,"
                    + "(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    + "login INTEGER,"
                    + "passportID VARCHAR (30),"
                    + "nameReader VARCHAR (30),"
                    + "surname VARCHAR (30),"
                    + "patronymic VARCHAR (30),"
//                    + "passwordID VARCHAR (30),"
                    + "phone VARCHAR (30),"
                    + "birthDay VARCHAR (30),"
                    + "FOREIGN KEY(id) REFERENCES LibraryUser(id))";


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

    public void insertAdmin(User user,Connection conn){
        String SQL = "INSERT INTO LibraryAdmin(login,password) "
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

    public void insertReader(Reader reader,Connection conn){
        String SQL = "INSERT INTO LibraryReader(login,passportID,nameReader,surname,patronymic,phone,birthDay) "
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, reader.getLogin());
            pstmt.setString(2, reader.getPassportID());
            pstmt.setString(3, reader.getName());
            pstmt.setString(4, reader.getSurname());
            pstmt.setString(5, reader.getPatronymic());
            pstmt.setString(6, reader.getPhone());
            pstmt.setString(7, reader.getBirthDay());
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean authorizationCheck(User user,Connection conn){
        boolean flag=false;

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryUser` WHERE (login ="+user.getLogin()+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            if (resultSet.next()){
                String g=resultSet.getString(3);
                if(g.equals(user.getPassword()))
                    flag=true;
                System.out.println("ты милашка!");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean authorizationAdminCheck(User user,Connection conn){
        boolean flag=false;

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryAdmin` WHERE (login ="+user.getLogin()+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            if (resultSet.next()){
                String g=resultSet.getString(3);
                if(g.equals(user.getPassword()))
                    flag=true;
                System.out.println("ты милашка!");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


}