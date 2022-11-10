//import org.jdatepicker.impl;

import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import java.util.*;
import java.util.Calendar;
import java.util.Date;
import org.jdatepicker.*;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;

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
//        b2.addActionListener(new ButtonAuthorizationListener());

        add(l1);
        add(b1);
        add(b2);


    }

class ButtonRegistrationListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame();
        frame.setSize(1000,600);
        frame.setLayout(null);
//        JDatePickerImpl datePicker;
//        setBackground(Color.RED);

        String[] daysBox = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String[] monthBox = {"январь","февраль","март","апрель","май","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
//        String[] YearBox = new String[100];
//        int j=0;
//        for(int i=1920;i<2022;i++){
//            YearBox[j]=Integer.toString(i);
//            j++;
////            System.out.print(i);
//        }




        JButton back= new JButton("Назад");
        JButton signUp = new JButton("Зарегистрироваться");
        JLabel title = new JLabel("Регистрация");
        JLabel name = new JLabel("ФИО: ");
        JLabel phone = new JLabel("Контактный телефон: ");
        JLabel birthday = new JLabel("Дата вашего рождения: ");
        JLabel loginLabel = new JLabel("login: ");
        JLabel passwordLabel = new JLabel("password: ");



        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        datePicker.setLocation(50,400);

        frame.add(datePicker);

//        Jco

//        title.setForeground(Color.GREEN);


        title.setSize(200,25);
        title.setLocation(450, 25);
        loginLabel.setSize(200,25);
        loginLabel.setLocation(200, 200);
        passwordLabel.setSize(200,40);
        passwordLabel.setLocation(500, 300);

        signUp.setBounds(400,460,160,25);
        back.setBounds(400,500,160,25);


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        frame.add(title);
        frame.add(loginLabel);
        frame.add(back);
        frame.add(signUp);

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
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}