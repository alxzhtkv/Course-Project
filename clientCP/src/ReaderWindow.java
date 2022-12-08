import javax.swing.*;
import java.awt.*;

public class ReaderWindow extends JFrame {
    JButton showPersonalDataBttn,managerOrderBttn,editPersonalDataBttn,favouritesBttn;
    ClientService clientService;
//    Menu main,books,setting;MenuItem item1;
    Container content;
//    JLabel hui,fg,a;

    public ReaderWindow(ClientService clientService) throws HeadlessException {
        this.clientService = clientService;
        setTitle("Личный кабинет");
        setSize(1000, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
//        content = getContentPane();
        managerOrderBttn = new JButton("Меню заказа");
        favouritesBttn = new JButton("Избранное");
        showPersonalDataBttn = new JButton("Данные личного кабинета");
        editPersonalDataBttn = new JButton("Изменить личные данные");

        managerOrderBttn.setBounds(400, 100, 220, 25);
        favouritesBttn.setBounds(400, 150, 220, 25);
        showPersonalDataBttn.setBounds(400, 200, 220, 25);
        editPersonalDataBttn.setBounds(400, 250, 220, 25);


        add(managerOrderBttn);
        add(favouritesBttn);
        add(showPersonalDataBttn);
        add(editPersonalDataBttn);

//        hui= new JLabel("Работаает1");
//        fg= new JLabel("Работаает2");
//        hui.setSize(200,25);
//        hui.setLocation(200,200);
//        fg.setSize(200,25);
//        fg.setLocation(100,100);
//
//        MenuBar mbar=new MenuBar();
//        setMenuBar(mbar);
////создать элемент меню
//        main=new Menu("main");
//        main.add(item1 = new MenuItem("бля"));
//        mbar.add(main);
//        books=new Menu("books");
//        mbar.add(books);
//        setting=new Menu("setting");
//        mbar.add(setting);

    }
}
