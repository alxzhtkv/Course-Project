import java.io.*;//импорт пакета, содержащего классы для
//ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для работы в
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
//сети Internet


public class Server {
    static ServerSocket serverSocket = null;
    static Socket clientAccepted     = null;//объявление объекта класса Socket
    static ObjectInputStream  sois   = null;//объявление байтового потока ввода
    static ObjectOutputStream soos   = null;









    public static void main(String[] arg) throws SQLException {//объявление объекта класса ServerSocket


        Database database = new Database();
        Connection connection=database.databaseConnection();
        Statement statement=database.createTable(connection);

//        int rows = statement.executeUpdate("INSERT libraryUser(login,password) VALUES ('ХУЙ','HIU')");
//        System.out.printf("Added %d rows", rows);
//        int rows = statement.executeUpdate("INSERT libraryReader(login,password,nameReader,surname,patronymic,passwordID,phone,birthDay) VALUES ('log','pass','Александра','Житкова','Евгеньевна','MC2902460','+375445119087','2002-10-16')");
//        System.out.printf("Added %d rows", rows);
//        String temp = "мяу";
//        database.addUserToTable(connection,temp,temp);






       //объявление байтового потока вывода
        while (true){

            try {
                System.out.println("server starting....");
                serverSocket = new ServerSocket(2525);//создание сокета сервера для //заданного порта
                clientAccepted = serverSocket.accept();//выполнение метода, который //обеспечивает реальное подключение сервера к клиенту
                System.out.println("connection established....");
//создание потока ввода soos = new
                sois = new ObjectInputStream(clientAccepted.getInputStream());

                soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока
//вывода
                User user = getUser();
                database.insertUser(user,connection);
                Reader reader = getReader();
                System.out.println(reader.getName());
                database.insertReader(reader,connection);

//                int rows = statement.executeUpdate("INSERT libraryUser(login,password) VALUES ('ХУЙ','HIU')");


//                String login = (String) sois.readObject();//объявление //строки и присваивание ей данных потока ввода, представленных
////в виде строки (передано клиентом)
//                String pass = (String) sois.readObject();

//                System.out.println("message recieved: " + login + "\n"+ pass);
////
////
//
//
//
//
//                String serverMessage = "mur";
//                soos.writeObject(serverMessage);

//                String clientMessageRecieved1 = (String) sois.readObject();
//                System.out.println("message recieved1: " + clientMessageRecieved1);











            }catch(Exception e)	{
            } finally {
                try {
                    sois.close();//закрытие потока ввода
                    soos.close();//закрытие потока вывода
                    clientAccepted.close();//закрытие сокета, выделенного для клиента
                    serverSocket.close();//закрытие сокета сервера
                } catch(Exception e) {
                    e.printStackTrace();//вызывается метод исключения е
                }
            }


        }

    }


    public static User getUser(){
        String login,pass;
        try {
            login = (String) sois.readObject();
            pass = (String) sois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("message recieved: " + login + "\n"+ pass);
        User usertemp=new User(login,pass);
        return usertemp;

    }

    public static Reader getReader(){
        String login,passport,password,name,surname,patronymic,phone,birthday;
        try {
            login = (String) sois.readObject();
            password= (String) sois.readObject();
            passport = (String) sois.readObject();
            name = (String) sois.readObject();
            surname = (String) sois.readObject();
            patronymic = (String) sois.readObject();
            phone = (String) sois.readObject();
            birthday = (String) sois.readObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("message recieved (reader)");
        Reader readertemp =new Reader(login,password,name,surname,patronymic,passport,phone,birthday);

        return readertemp;

    }
}
