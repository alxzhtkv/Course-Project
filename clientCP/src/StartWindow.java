//import org.jdatepicker.impl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


import javax.imageio.ImageIO;
import javax.swing.*;


public class StartWindow extends JFrame {
    ClientService clientService;
    private static Image background;
    static JLabel l1,l2;
    Container content;

    JButton startButton,b1,b2;
    ObjectOutputStream coos;

    public StartWindow(ClientService clientService) {
        this.clientService=clientService;




        setTitle("Library");
        setLayout(null);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);


        setContentPane(new Background()); // панель
        content = getContentPane(); //теперь

        l1 = new JLabel("Хуй");
        l1.setSize(200,40);
        l1.setLocation(500, 300);

        b1 = new JButton("Регистрация");
        b2 = new JButton("Вход");



        b1.addActionListener(new ButtonRegistrationListener());
        b2.addActionListener(new ButtonAuthorizationListener());
//        b2.addActionListener(new ButtonAuthorizationListener());

        content.add(l1);
        content.add(b1);
        content.add(b2);


    }

class ButtonRegistrationListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        new RegistrationWindow(clientService).setVisible(true);
        setVisible(false);

    }
}

    class ButtonAuthorizationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new AuthorizationWindow(clientService).setVisible(true);

        }
    }



    private static class Background extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                background = ImageIO.read(new File("images/background1.jpeg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(background, 0, 0, null);
            setLayout(null);
        }
    }


    private static class BackgroundRegistration extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                background = ImageIO.read(new File("images/backgroundRegistration1.jpeg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(background, 0, 0, null);
            setLayout(null);
        }
    }

}