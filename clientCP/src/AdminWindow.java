import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminWindow extends JFrame {
    ClientService clientService;
    JButton workWithBooks, workWithJournals, workWithUsers, workWithOrders, workWithAdmins;
    JLabel adminMenu;
    JDialog dialog;
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

        addButton = new JButton("Добавить книгу");
        showButton = new JButton("Добавить книгу");
        deleteButton = new JButton("Добавить книгу");

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


    }

    public class addingBookActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new AddingBooksWindow(clientService).setVisible(true);


        }
    }
}
