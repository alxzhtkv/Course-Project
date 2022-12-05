import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class AdminBooksService extends JFrame {
    ClientService clientService;
    JButton addButton, deleteButton,backTableButton,sortButton,searchButton,editingButton;
    Vector<Book> booksVector;


    public AdminBooksService(ClientService clientService) throws HeadlessException {
        this.clientService = clientService;
        this.setSize(1000, 600);
        this.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        backTableButton = new JButton("Назад");
        sortButton = new JButton("Сортировать");
        searchButton = new JButton("Поиск");
        addButton = new JButton("Добавить");
        deleteButton = new JButton("Удалить");
        editingButton = new JButton("Редактировать");

        backTableButton.setBounds(150,460,100,25);
        sortButton.setBounds(300,460,100,25);
        searchButton.setBounds(450,460,100,25);
        addButton.setBounds(600, 460, 100, 25);
        deleteButton.setBounds(750, 460, 100, 25);
        editingButton.setBounds(900,460,100,25);


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
        booksVector = new Vector<>();
               while (count!=0){

            Book book=clientService.getBookFromDatabase();
            booksVector.add(book);



            count--;
        }
        System.out.println(booksVector.size());

        String[] columnsHeader = new String[] {"№", "ID", "Название", "Автор", "Издательство", "Жанр", "Год издания", "Количетсво"};
        DefaultTableModel dtm=new DefaultTableModel(columnsHeader,0);
        JTable tbl=new JTable(dtm);
        dtm.addRow(columnsHeader);
        for(int i=0;i<size;i++){
            String[] item ={Integer.toString(i+1),booksVector.get(i).getID(),booksVector.get(i).getTitle(),booksVector.get(i).getAuthor(),booksVector.get(i).getPublisher(),booksVector.get(i).getGenre(),booksVector.get(i).getYear(),booksVector.get(i).getCount()};
            dtm.addRow(item);
        }
        JScrollPane tableContainer = new JScrollPane(tbl);
        this.add(tableContainer,BorderLayout.CENTER);



        this.add(tableContainer);
        add(backTableButton);
        add(sortButton);
        add(searchButton);
        add(addButton);
        add(deleteButton);
        add(editingButton);

        addButton.addActionListener(new addingBookActionListener());


    }

    public class addingBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new AddingBooksWindow(clientService).setVisible(true);


        }
    }
}
