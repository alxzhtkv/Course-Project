import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AuthorizationWindow extends JFrame {
    ClientService clientService;
    JTextField passwordTextField,loginTextField;
    JLabel error, loginL, passwordL;
    JButton back,signIn;
    Container content;
    String message;

    JCheckBox checkBoxAdmin;

    public AuthorizationWindow(ClientService clientService) throws HeadlessException {
        this.clientService = clientService;

        String mess= "authorization";
        try {
            clientService.coos.writeObject(mess);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setSize(1000,600);
        this.setLayout(null);

        setLocationRelativeTo(null);
        setResizable(false);
        content = getContentPane();

        setTitle("Вход");
        back= new JButton("Назад");
        signIn = new JButton("Войти");

        loginL=new JLabel("Введите ваш логин: ");
        passwordL=new JLabel("Введите ваш пароль: ");
        error=new JLabel();

        loginTextField = new JTextField();
        passwordTextField = new JTextField();
        checkBoxAdmin = new JCheckBox("Администатор");

        loginL.setSize(200,25);
        loginL.setLocation(300,200);
        passwordL.setSize(200,25);
        passwordL.setLocation(300,250);

        loginTextField.setSize(200,25);
        loginTextField.setLocation(480,200);
        passwordTextField.setSize(200,25);
        passwordTextField.setLocation(480,250);


        signIn.setBounds(400,460,160,25);
        back.setBounds(400,500,160,25);
        checkBoxAdmin.setBounds(10,500,200,25);


        error.setSize(200,25);
        error.setLocation(400,400);


        content.add(loginL);
        content.add(passwordL);
        content.add(error).setVisible(false);
        content.add(loginTextField);
        content.add(passwordTextField);
        content.add(signIn);
        content.add(back);
        content.add(checkBoxAdmin);

        signIn.addActionListener(new signInActionListener());



    }
     public class signInActionListener implements ActionListener{

         public void actionPerformed(ActionEvent e) {
             String login;
             String password;
             String serverAnswer;



             login=loginTextField.getText();
             password=passwordTextField.getText();
             User user = new User(login,password);
             clientService.sendUser(user);
             if(checkBoxAdmin.isSelected()){
                 message="admin";
                 try {
                     clientService.coos.writeObject(message);
                 } catch (IOException ex) {
                     throw new RuntimeException(ex);
                 }
             }
             else {
                 message="user";
                 try {
                     clientService.coos.writeObject(message);
                 } catch (IOException ex) {
                     throw new RuntimeException(ex);
                 }
             }


             try {
                 serverAnswer = (String) clientService.cois.readObject();
             } catch (IOException ex) {
                 throw new RuntimeException(ex);
             } catch (ClassNotFoundException ex) {
                 throw new RuntimeException(ex);
             }
             System.out.println(serverAnswer);
             switch (serverAnswer){
                 case "approved":{
                     error.setText("Авторизация успешно завершена!");
                     error.setVisible(true);
                     new ReaderWindow(clientService).setVisible(true);

                     break;
                 }
                 case "approvedAdmin":{
                     error.setText("Авторизация админа завершена!");
                     error.setVisible(true);
                     new AdminWindow(clientService).setVisible(true);

                     break;
                 }
                 case "refused":{
                     error.setText("Неверный логин или пароль");
                     error.setVisible(true);
                     break;
                 }

             }
         }

     }



}
