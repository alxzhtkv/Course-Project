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


        try {
            System.out.println("server connecting....");
            Socket clientSocket = new Socket("127.0.0.1",2525);//установление //соединения между локальной машиной и указанным портом узла сети
            System.out.println("connection established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));//создание//буферизированного символьного потока ввода
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());//создание//потока вывода
            ObjectInputStream  cois = new ObjectInputStream(clientSocket.getInputStream());//создание//потока ввода
            new StartWindow().setVisible(true);








//            Matrix matrix = new Matrix(n,n,arr);
//            System.out.println("the resulting matrix: " );
//            matrix.printMatrix();
//
            String clientMessage = "mew";

            coos.writeObject(clientMessage);//потоку вывода присваивается //значение строковой переменной (передается серверу)

            System.out.println("~server~ \nMatrix determinant:\n "+cois.readObject());


            coos.close();//закрытие потока вывода
            cois.close();//закрытие потока ввода
            clientSocket.close();//закрытие сокета
        }catch(Exception e)	{
            e.printStackTrace();//выполнение метода исключения е
        }

    }


}
