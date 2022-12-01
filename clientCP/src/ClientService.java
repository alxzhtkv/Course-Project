import java.io.*;//импорт пакета, содержащего классы для
// ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для
// работы в сети
import java.io.Serializable;
import java.util.Arrays;

import java.io.IOException;
import java.util.Scanner;

public class ClientService {
//    User user;
    ObjectOutputStream coos=null;
    ObjectInputStream cois=null;
    Socket clientSocket=null;

    public  ClientService(){


    }

    public void getConnection(){
        try {
            System.out.println("server connecting....");
            clientSocket = new Socket("127.0.0.1",2525);//установление //соединения между локальной машиной и указанным портом узла сети
            System.out.println("connection established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));//создание//буферизированного символьного потока ввода
            coos = new ObjectOutputStream(clientSocket.getOutputStream());//создание//потока вывода
            cois = new ObjectInputStream(clientSocket.getInputStream());//создание//потока ввода



        }catch(Exception e)	{
            e.printStackTrace();//выполнение метода исключения е
            endConnection();
        }
    }

    public void endConnection(){

        try{
            coos.close();//закрытие потока вывода
            cois.close();//закрытие потока ввода
            clientSocket.close();//закрытие сокета
            System.out.println("server disconnecting....");
        }catch(Exception e)	{
            e.printStackTrace();//выполнение метода исключения е
        }
    }

    public void sendUser(User user){
        try {
            coos.writeObject(user.getLogin());
            coos.writeObject(user.getPassword());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendReader(Reader reader){
        try {
            coos.writeObject(reader.getLogin());
            coos.writeObject(reader.getPassword());
            coos.writeObject(reader.getPassportID());
            coos.writeObject(reader.getName());
            coos.writeObject(reader.getSurname());
            coos.writeObject(reader.getPatronymic());
            coos.writeObject(reader.getPhone());
            coos.writeObject(reader.getBirthDay());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
