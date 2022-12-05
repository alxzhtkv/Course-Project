import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class AdminWindow extends JFrame {
    ClientService clientService;
    JButton workWithBooks, workWithJournals, workWithUsers, workWithOrders, workWithAdmins;
    JLabel adminMenu;
    JScrollPane tableContainer;

    JDialog dialogShowBooks,deleteDialog;
    JButton addButton, showButton, deleteButton,backTableButton,sortButton,searchButton;

    JButton edit;
    JTextField editField;
    Vector<Book> booksVector;


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


        showButton = new JButton("Просмотреть фонд библиотеки");

        backTableButton = new JButton("Назад");
        sortButton = new JButton("Сортировать");
        searchButton = new JButton("Поиск");
        addButton = new JButton("Добавить");
        deleteButton = new JButton("Удалить");


        dialogShowBooks = new JDialog();
        deleteDialog = new JDialog();














        showButton.setBounds(100, 150, 200, 25);


        adminMenu.setBounds(400, 90, 220, 25);
        workWithBooks.setBounds(400, 100, 220, 25);
        workWithJournals.setBounds(400, 150, 220, 25);
        workWithUsers.setBounds(400, 200, 220, 25);
        workWithOrders.setBounds(400, 250, 220, 25);
        workWithAdmins.setBounds(400, 300, 220, 25);





        backTableButton.setBounds(150,460,100,25);
        sortButton.setBounds(300,460,100,25);
        searchButton.setBounds(450,460,100,25);
        addButton.setBounds(600, 460, 100, 25);
        deleteButton.setBounds(750, 460, 100, 25);

        dialogShowBooks.setBounds(0, 0, 1000, 600);
        dialogShowBooks.setLocationRelativeTo(null);

        dialogShowBooks.add(backTableButton);
        dialogShowBooks.add(sortButton);
        dialogShowBooks.add(searchButton);
        dialogShowBooks.add(addButton);
        dialogShowBooks.add(deleteButton);
//        dialogShowBooks.setLayout(null);





//        dialog.setResizable(false);


//        dialog.add(addButton);

//        dialog.add(deleteButton);

        add(workWithBooks);
        add(workWithJournals);
        add(workWithUsers);
        add(workWithOrders);
        add(workWithAdmins);
//        add(dialog).setVisible(false);


        workWithBooks.addActionListener(new showBookActionListener());

        addButton.addActionListener(new addingBookActionListener());
        deleteButton.addActionListener(new deleteBookActionListener());
//        showButton.addActionListener(new showBookActionListener());




    }

    public class addingBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new AddingBooksWindow(clientService).setVisible(true);


        }
    }

    public class showBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

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
            int count=size;
            int j=0;


            booksVector = new Vector<>();
            ArrayList<Book> booksList=new ArrayList<>();
            while (count!=0){
                System.out.println("происходит пробитие");
                Book book=clientService.getBookFromDatabase();
                booksVector.add(book);
                booksList.add(book);
                booksVector.get(0).getAuthor();

                count--;
            }
            System.out.println(booksVector.size());


            String[] columnsHeader = new String[] {"№", "ID", "Название", "Автор", "Издательство", "Жанр", "Год издания", "Количетсво"};
            DefaultTableModel dtm=new DefaultTableModel(columnsHeader,0);
            JTable tbl=new JTable(dtm);
            for(int i=0;i<size;i++){
                String[] item ={Integer.toString(i+1),booksVector.get(i).getID(),booksVector.get(i).getTitle(),booksVector.get(i).getAuthor(),booksVector.get(i).getPublisher(),booksVector.get(i).getGenre(),booksVector.get(i).getYear(),booksVector.get(i).getCount()};
                dtm.addRow(item);
            }

//            }



            tableContainer = new JScrollPane(tbl);

            dialogShowBooks.add(tableContainer,BorderLayout.CENTER);


//            dialogShowBooks.add(tbl);
            dialogShowBooks.setVisible(true);

        }
    }

    public class deleteBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dialogShowBooks.setBounds(0, 0, 500, 500);
            dialogShowBooks.setLocationRelativeTo(null);
            dialogShowBooks.setLayout(null);
//            tableContainer.get типа получить ячейку для редактирования

            dialogShowBooks.setVisible(true);

        }
    }
}
