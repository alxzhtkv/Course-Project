import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

public class AdminWindow extends JFrame {
    String mess;
    ClientService clientService;
    JButton workWithBooks, workWithJournals, workWithUsers, workWithOrders, workWithAdmins;
    JLabel adminMenu;
    JScrollPane tableContainer;

    JDialog dialogShowBooks, deleteDialog, BooksManagerMenu;
    JButton addButton, showButton, deleteButton, backTableButton, sortButton, searchButton;

    JButton edit;
    JTextField editField,deleteByIdTextField;
    Vector<Book> booksVector;
    JTable tblBooks;


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


        showButton = new JButton("Просмотреть книги");
        backTableButton = new JButton("Назад");
        sortButton = new JButton("Сортировать");
        searchButton = new JButton("Поиск");
        addButton = new JButton("Добавить");
        deleteButton = new JButton("Удалить");


        dialogShowBooks = new JDialog();
        deleteDialog = new JDialog();
        BooksManagerMenu = new JDialog();

        BooksManagerMenu.setLayout(null);


        adminMenu.setBounds(400, 90, 220, 25);
        workWithBooks.setBounds(400, 100, 220, 25);
        workWithJournals.setBounds(400, 150, 220, 25);
        workWithUsers.setBounds(400, 200, 220, 25);
        workWithOrders.setBounds(400, 250, 220, 25);
        workWithAdmins.setBounds(400, 300, 220, 25);


        showButton.setBounds(100, 100, 200, 25);
        backTableButton.setBounds(150, 460, 100, 25);
        sortButton.setBounds(100, 200, 200, 25);
        searchButton.setBounds(100, 250, 200, 25);
        addButton.setBounds(100, 150, 200, 25);
        deleteButton.setBounds(100, 300, 200, 25);
//        updateTableButton.setBounds(900,460,100,25);

        dialogShowBooks.setBounds(0, 0, 1000, 600);
        dialogShowBooks.setLocationRelativeTo(null);

        BooksManagerMenu.setBounds(0, 0, 400, 400);
        BooksManagerMenu.setLocationRelativeTo(null);

        BooksManagerMenu.add(showButton);
        BooksManagerMenu.add(addButton);

        BooksManagerMenu.add(sortButton);
        BooksManagerMenu.add(searchButton);
        BooksManagerMenu.add(deleteButton);


//        dialogShowBooks.add(backTableButton);
//        dialogShowBooks.add(sortButton);
//        dialogShowBooks.add(searchButton);
//        dialogShowBooks.add(addButton);
//        dialogShowBooks.add(deleteButton);
//        dialogShowBooks.add(updateTableButton);

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


        workWithBooks.addActionListener(new BooksManagerActionListener());

        addButton.addActionListener(new addingBookActionListener());
        deleteButton.addActionListener(new deleteBookActionListener());
        showButton.addActionListener(new ShowBookActionListener());

    }

    public class BooksManagerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BooksManagerMenu.setVisible(true);
        }
    }

    public class addingBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new AddingBooksWindow(clientService).setVisible(true);
        }
    }


    public class deleteBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            mess = "deleteBook";
            try {
                clientService.coos.writeObject(mess);

            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }

            JDialog newDialog = new JDialog();
            newDialog.setLayout(null);

            JButton delBttn=new JButton("Удалить");
            JButton okButtn=new JButton("Ок");

            JLabel IDTextField=new JLabel("Введите ID книги для удаления");
            newDialog.setBounds(0, 0, 400, 400);
            JLabel error = new JLabel();

            deleteByIdTextField = null;
            try {
                deleteByIdTextField = new JFormattedTextField(
                        new MaskFormatter("#####"));
//                deleteByIdTextField.setColumns(5);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            IDTextField.setBounds(100,100,200,25);
            deleteByIdTextField.setBounds(100,150,200,25);
            delBttn.setBounds(150,300,100,25);
            okButtn.setBounds(150,100,100,25);
            error.setBounds(150,50,200,25);


//            IDTextField.setBounds(100,100,100,25);
//            deleteByIdTextField.setBounds(100,200,50,25);
//            delBttn.setBounds(100,300,50,25);

            newDialog.add(IDTextField);
            newDialog.add(deleteByIdTextField);
            newDialog.add(delBttn);


            newDialog.setLocationRelativeTo(null);
            newDialog.setVisible(true);

            JDialog errorDialog = new JDialog();
            errorDialog.setSize( 400, 200);
            errorDialog.setLayout(null);
            errorDialog.setVisible(false);
            errorDialog.setLocationRelativeTo(null);

            errorDialog.setResizable(false);


            errorDialog.add(okButtn);
            errorDialog.add(error);
            okButtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    errorDialog.setVisible(false);
                }
            });


            delBttn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    String id=deleteByIdTextField.getText();



                    try {
                        clientService.coos.writeObject(id);
                        mess=(String) clientService.cois.readObject();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    switch (mess) {
                        case "deleted": {
                            error.setText("Запись удалена");
                            errorDialog.setVisible(true);
                            newDialog.setVisible(false);
//                            error.setText("Запись удалена");
//                            error.setVisible(true);



                            break;
                        }
                        case "error": {
                            error.setText("Запись не найдена");
                            errorDialog.setVisible(true);
                            newDialog.setVisible(false);
//                            error.setText("Запись не найдена");
//                            error.setVisible(true);



                            break;
                        }
                    }


                }
            });


        }
    }

    public class ShowBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            String mess = "showBooks";
            try {
                clientService.coos.writeObject(mess);
                mess = (String) clientService.cois.readObject();
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            int size = Integer.valueOf(mess);
            int count = size;
            int j = 0;


            Vector<Book> booksVector = new Vector<>();

            while (count != 0) {
                System.out.println("происходит пробитие");
                Book book = clientService.getBookFromDatabase();
                booksVector.add(book);

                booksVector.get(0).getAuthor();

                count--;
            }
            System.out.println(booksVector.size());


            String[] columnsHeader = new String[]{"№", "ID", "Название", "Автор", "Издательство", "Жанр", "Год издания", "Количетсво"};
            DefaultTableModel dtm = new DefaultTableModel(columnsHeader, 0);
            JTable tbl = new JTable(dtm);

            for (int i = 0; i < size; i++) {
                String[] item = {Integer.toString(i + 1), booksVector.get(i).getID(), booksVector.get(i).getTitle(), booksVector.get(i).getAuthor(), booksVector.get(i).getPublisher(), booksVector.get(i).getGenre(), booksVector.get(i).getYear(), booksVector.get(i).getCount()};
                dtm.addRow(item);
            }
            tbl.updateUI();

            JScrollPane tableContainer = new JScrollPane(tbl);
            JDialog newDialog = new JDialog();
            newDialog.setBounds(0, 0, 1000, 600);
            newDialog.setLocationRelativeTo(null);

            newDialog.add(tableContainer, BorderLayout.CENTER);
            newDialog.setVisible(true);

        }

    }
}

//    public void showBooks(){
//
//
//        String mess= "showBooks";
//        try {
//            clientService.coos.writeObject(mess);
//            mess= (String) clientService.cois.readObject();
//        } catch (IOException e1) {
//            throw new RuntimeException(e1);
//        } catch (ClassNotFoundException ex) {
//            throw new RuntimeException(ex);
//        }
//        int size=Integer.valueOf(mess);
//        int count=size;
//        int j=0;
//
//
//        booksVector = new Vector<>();
//
//        while (count!=0){
//            System.out.println("происходит пробитие");
//            Book book=clientService.getBookFromDatabase();
//            booksVector.add(book);
//
//            booksVector.get(0).getAuthor();
//
//            count--;
//        }
//        System.out.println(booksVector.size());
//
//
//        String[] columnsHeader = new String[] {"№", "ID", "Название", "Автор", "Издательство", "Жанр", "Год издания", "Количетсво"};
//        DefaultTableModel dtm=new DefaultTableModel(columnsHeader,0);
//        JTable tbl=new JTable(dtm);
//        tableContainer = new JScrollPane(tbl);
//
//        for(int i=0;i<size;i++){
//            String[] item ={Integer.toString(i+1),booksVector.get(i).getID(),booksVector.get(i).getTitle(),booksVector.get(i).getAuthor(),booksVector.get(i).getPublisher(),booksVector.get(i).getGenre(),booksVector.get(i).getYear(),booksVector.get(i).getCount()};
//            dtm.addRow(item);
//        }
//        tbl.updateUI();
//
//
//        tableContainer.revalidate();
////        tableContainer.updateUI();
////            tableContainer.updateUI();
//
//        dialogShowBooks.add(tableContainer,BorderLayout.CENTER);
//    }
//
//
//    public void showBook(){
//
//
//        String mess= "showBooks";
//        try {
//            clientService.coos.writeObject(mess);
//            mess= (String) clientService.cois.readObject();
//        } catch (IOException e1) {
//            throw new RuntimeException(e1);
//        } catch (ClassNotFoundException ex) {
//            throw new RuntimeException(ex);
//        }
//        int size=Integer.valueOf(mess);
//        int count=size;
//        int j=0;
//
//
//        Vector <Book>booksVector = new Vector<>();
//
//        while (count!=0){
//            System.out.println("происходит пробитие");
//            Book book=clientService.getBookFromDatabase();
//            booksVector.add(book);
//
//            booksVector.get(0).getAuthor();
//
//            count--;
//        }
//        System.out.println(booksVector.size());
//
//
//        String[] columnsHeader = new String[] {"№", "ID", "Название", "Автор", "Издательство", "Жанр", "Год издания", "Количетсво"};
//        DefaultTableModel dtm=new DefaultTableModel(columnsHeader,0);
//        JTable tbl=new JTable(dtm);
//
//        for(int i=0;i<size;i++){
//            String[] item ={Integer.toString(i+1),booksVector.get(i).getID(),booksVector.get(i).getTitle(),booksVector.get(i).getAuthor(),booksVector.get(i).getPublisher(),booksVector.get(i).getGenre(),booksVector.get(i).getYear(),booksVector.get(i).getCount()};
//            dtm.addRow(item);
//        }
//        tbl.updateUI();
//
//        JScrollPane tableContainer = new JScrollPane(tbl);
//        JDialog newDialog=new JDialog();
//        newDialog.setBounds(0, 0, 1000, 600);
//        newDialog.setLocationRelativeTo(null);
//
//        newDialog.add(tableContainer,BorderLayout.CENTER);
//        newDialog.setVisible(true);
//
//
////        tableContainer.updateUI();
////            tableContainer.updateUI();
//
//
////        dialogShowBooks.add(tableContainer,BorderLayout.CENTER);
//    }
//
//
//
//}
