import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class AddingBooksWindow extends JFrame {
    ClientService clientService;
    JTextField titleField,publisherTextField,yearTextField, countTextField,authorTextField;
    JComboBox genres;
    JLabel ID,error;
    String id;
    JButton backButton,addButton;
    Container content;


    public AddingBooksWindow(ClientService clientService) throws HeadlessException {

        this.clientService = clientService;

        this.setSize(1000,600);
        this.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        content = getContentPane();

        String[]genreBox={"Роман","Детектив","Роман","Приключения","Фантастика","Фэнтези","Сказка", "Философия"};

        setTitle("Регистрация");
        backButton= new JButton("Назад");
        addButton = new JButton("Добавить");

        JLabel L = new JLabel("Добавление книги");
        JLabel IDl = new JLabel("ID: ");
        JLabel titleL = new JLabel("Название книги: ");
        JLabel authorL = new JLabel("Автор: ");
        JLabel publisherL = new JLabel("Издательство: ");
        JLabel genreL = new JLabel("Жанр: ");
        JLabel yearL = new JLabel("Год издания: ");
        JLabel countL = new JLabel("Количество: ");

        titleField = new JTextField();
        authorTextField = new JTextField();
        publisherTextField = new JTextField();
        genres=new JComboBox<>(genreBox);

        yearTextField = new JTextField();
        countTextField=new JFormattedTextField();

        Random random = new Random();
        id=String.valueOf(random.nextInt(100000)+1);
        ID=new JLabel(id);

        IDl.setBounds(30,20,200,25);
        ID.setBounds(280,20,200,25);

        titleL.setBounds(30,50,200,25);
        titleField.setBounds(280,50,200,25);

        authorL.setBounds(30,80,200,25);
        authorTextField.setBounds(280,80,200,25);

        publisherL.setBounds(30,110,200,25);
        publisherTextField.setBounds(280,110,200,25);

        genreL.setBounds(30,140,200,25);
        genres.setBounds(280,140,200,25);

        yearL.setBounds(30,170,200,25);
        yearTextField.setBounds(280,170,200,25);

        countL.setBounds(30,200,200,25);
        countTextField.setBounds(280,200,200,25);

        backButton.setBounds(400,460,160,25);
        addButton.setBounds(400,500,160,25);



        add(IDl);
        add(ID);
        add(titleL);
        add(titleField);
        add(authorL);
        add(authorTextField);
        add(publisherL);
        add(publisherTextField);
        add(genreL);
        add(genres);
        add(yearL);
        add(yearTextField);
        add(countL);
        add(countTextField);
        add(addButton);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.setVisible(false);
            }
        });

        addButton.addActionListener(new addBookActionListener());

    }

    public class addBookActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {


            String mess= "addingBook";
            try {
                clientService.coos.writeObject(mess);
            } catch (IOException ee) {
                throw new RuntimeException(ee);
            }
            String ID;
            String title;
            String publisher;
            String genre;
            String year;
            String count;
            String author;

//            id
            title=titleField.getText();
            publisher=publisherTextField.getText();
            genre=genres.getSelectedItem().toString();
            System.out.println(genre);
            year=yearTextField.getText();
            count=countTextField.getText();
            author=authorTextField.getText();

            Book book=new Book(id,title,publisher,genre,year,count,author);
            clientService.sendBook(book);









        }

    }



}
