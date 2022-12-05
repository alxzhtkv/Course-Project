import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class AdminWindow extends JFrame {
    ClientService clientService;
    JButton workWithBooks, workWithJournals, workWithUsers, workWithOrders, workWithAdmins;
    JLabel adminMenu;
    JDialog dialog;
    JDialog dialogShowBooks;
    JButton addButton, showButton, deleteButton;


//    JLabel

    public AdminWindow(ClientService clientService) throws HeadlessException {
        this.clientService = clientService;
        this.setSize(1000, 600);
        this.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        adminMenu = new JLabel("Меню администратора");
        workWithBooks = new JButton("Работа с книгами");
        workWithJournals = new JButton("Работа с журналами");
        workWithUsers = new JButton("Управление пользователями");
        workWithOrders = new JButton("Управление заказами");
        workWithAdmins = new JButton("Добавление администратора");
        dialog = new JDialog();
        dialogShowBooks = new JDialog();

        addButton = new JButton("Добавить книгу");
        showButton = new JButton("Просмотреть фонд библиотеки");
        deleteButton = new JButton("Удалить книги книгу");


        String[] columnsHeader = new String[] {"№", "ID", "Название", "Автор", "Издательство", "Жанр", "Год издания", "Количетсво"};
        DefaultTableModel dtm=new DefaultTableModel(columnsHeader,0);
        JTable tbl=new JTable(dtm);
        String[] item={"A","B","C","D","e","f","g","h"};
        dtm.addRow(item);


        JScrollPane tableContainer = new JScrollPane(tbl);

        dialogShowBooks.add(tableContainer,BorderLayout.CENTER);








        addButton.setBounds(100, 100, 200, 25);
        showButton.setBounds(100, 150, 200, 25);
        deleteButton.setBounds(100, 200, 200, 25);

        adminMenu.setBounds(400, 90, 220, 25);
        workWithBooks.setBounds(400, 100, 220, 25);
        workWithJournals.setBounds(400, 150, 220, 25);
        workWithUsers.setBounds(400, 200, 220, 25);
        workWithOrders.setBounds(400, 250, 220, 25);
        workWithAdmins.setBounds(400, 300, 220, 25);

        dialog.setBounds(0, 0, 400, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);

        dialogShowBooks.setBounds(0, 0, 1000, 600);
        dialogShowBooks.setLocationRelativeTo(null);
//        dialogShowBooks.setLayout(null);


        dialogShowBooks.add(tbl);


//        dialog.setResizable(false);


        dialog.add(addButton);
        dialog.add(showButton);
        dialog.add(deleteButton);

        add(workWithBooks);
        add(workWithJournals);
        add(workWithUsers);
        add(workWithOrders);
        add(workWithAdmins);
//        add(dialog).setVisible(false);


        workWithBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(true);
            }
        });

        addButton.addActionListener(new addingBookActionListener());
        showButton.addActionListener(new showBookActionListener());


    }

    public class addingBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new AddingBooksWindow(clientService).setVisible(true);


        }
    }

    public class showBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dialogShowBooks.setVisible(true);
            String mess= "showBooks";
            try {
                clientService.coos.writeObject(mess);
                mess= (String) clientService.cois.readObject();
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            int size=Integer.valueOf(mess);
            Vector<Book> booksVector = new Vector<>();
            while (size!=0){
                Book book=clientService.getBookFromDatabase();
                booksVector.add(book);
                size--;
            }
            System.out.println(booksVector.size());


        }
    }
}
