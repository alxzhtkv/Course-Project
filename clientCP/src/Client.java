import java.io.*;//импорт пакета, содержащего классы для
// ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для
// работы в сети
import java.io.Serializable;
import java.util.Arrays;

import java.io.IOException;
import java.util.Scanner;


public class Client {

    public static void main(String[] arg) {

        Scanner in = new Scanner(System.in);

        ClientService clientService = new ClientService();
//        clientService.getConnection();
        new StartWindow().setVisible(true);
        User user = new User("1","1");
        clientService.sendUser(user);

//        clientService.endConnection();
//
//
//        try {
//            System.out.println("server connecting....");
//            Socket clientSocket = new Socket("127.0.0.1",2525);//установление //соединения между локальной машиной и указанным портом узла сети
//            System.out.println("connection established....");
//            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));//создание//буферизированного символьного потока ввода
//            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());//создание//потока вывода
//            ObjectInputStream  cois = new ObjectInputStream(clientSocket.getInputStream());//создание//потока ввода
//
//
//            coos.close();//закрытие потока вывода
//            cois.close();//закрытие потока ввода
//            clientSocket.close();//закрытие сокета
//        }catch(Exception e)	{
//            e.printStackTrace();//выполнение метода исключения е
//        }

    }


}
