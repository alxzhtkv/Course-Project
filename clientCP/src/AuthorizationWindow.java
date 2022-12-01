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

        setTitle("Вход");
        back= new JButton("Назад");
        signIn = new JButton("Войти");
        loginL=new JLabel("Введите ваш логин: ");
        passwordL=new JLabel("Введите ваш пароль: ");

        loginTextField = new JTextField();
        passwordTextField = new JTextField();

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


        add(loginL);
        add(passwordL);
        add(loginTextField);
        add(passwordTextField);
        add(signIn);
        add(back);

        signIn.addActionListener(new signInActionListener());



    }
     public class signInActionListener implements ActionListener{

         public void actionPerformed(ActionEvent e) {
             String login;
             String password;

             login=loginTextField.getText();
             password=passwordTextField.getText();
             User user = new User(login,password);
             clientService.sendUser(user);
         }

     }



}
