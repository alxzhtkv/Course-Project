import java.io.*;//импорт пакета, содержащего классы для
//ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для работы в
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
//сети Internet


public class Server {








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






        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;//объявление объекта класса Socket
        ObjectInputStream  sois   = null;//объявление байтового потока ввода
        ObjectOutputStream soos   = null;//объявление байтового потока вывода
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

                String clientMessageRecieved = (String) sois.readObject();//объявление //строки и присваивание ей данных потока ввода, представленных
//в виде строки (передано клиентом)

                System.out.println("message recieved: " + clientMessageRecieved);






                String serverMessage = "mur";
                soos.writeObject(serverMessage);











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
}
