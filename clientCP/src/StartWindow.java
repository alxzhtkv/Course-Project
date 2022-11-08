import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.*;





public class StartWindow extends JFrame {
    private static Image background;
    static JLabel l1,l2;

    JButton startButton,b1,b2;

    public StartWindow() {

        setTitle("Library");
        setLayout(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);


        setContentPane(new Background()); // панель
        Container content = getContentPane(); //теперь

        l1 = new JLabel("Хуй");
        l1.setSize(200,40);
        l1.setLocation(500, 300);

        b1 = new JButton("Регистрация");
        b2 = new JButton("Вход");



        b1.addActionListener(new ButtonRegistrationListener());

        add(l1);
        add(b1);
        add(b2);


    }

class ButtonRegistrationListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame();
        frame.setSize(1000,600);
        frame.setLayout(null);
        setBackground(Color.RED);


        JLabel l4 = new JLabel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        l4.setSize(200,40);
        l4.setLocation(500, 300);
        frame.add(l4);

//        b2.setSize(100, 30);
//        b2.setLocation(500, 100);
//        frame.add(b2);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
//        frame.setContentPane(new BackgroundRegistration());

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