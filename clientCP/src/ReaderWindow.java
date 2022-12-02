import javax.swing.*;
import java.awt.*;

public class ReaderWindow extends JFrame {
    ClientService clientService;
    Menu main,books,setting;MenuItem item1;
    Container content;
    JLabel hui,fg,a;

    public ReaderWindow(ClientService clientService) throws HeadlessException {
        this.clientService = clientService;
        setTitle("Личный кабинет");
        setLayout(null);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        content = getContentPane();

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
