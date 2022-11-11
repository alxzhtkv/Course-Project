import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

public class RegistrationWindow extends JFrame  {
    public RegistrationWindow(){
//        JFrame frame = new JFrame();
        this.setSize(1000,600);
        this.setLayout(null);
        JButton back= new JButton("Назад");
        JButton signUp = new JButton("Зарегистрироваться");
        JLabel title = new JLabel("Регистрация");
        JLabel nameLabel = new JLabel("Ваше ФИО: ");
        JLabel phoneLabel = new JLabel("Контактный телефон: ");
        JLabel birthdayLabel = new JLabel("Дата вашего рождения: ");
        JLabel passportIDLabel = new JLabel("Серия и номер вашего паспорта: ");
        JLabel loginLabel = new JLabel("ID (login): ");
        JLabel loginPS = new JLabel("(Ваш ID сгенерирован автоматически и является Вашим логином для входа) ");

        JLabel passwordLabel = new JLabel("Придумайте пароль: ");
        JLabel passwordCheckL = new JLabel("Повторите пароль: ");

        JTextField nameTextField =new JTextField();
        JFormattedTextField phoneTextField = null;
        try {
            phoneTextField = new JFormattedTextField(
                    new MaskFormatter("+375-##-###-##-##"));
            phoneTextField.setColumns(12);
        }catch (ParseException parseException){
            parseException.printStackTrace();
        }

        JFormattedTextField passportIDTextField = null;
        try {
            passportIDTextField = new JFormattedTextField(
                    new MaskFormatter("UU#######"));
            passportIDTextField.setColumns(9);
        }catch (ParseException parseException){
            parseException.printStackTrace();
        }


        Random random = new Random();
        String loginID =String.valueOf(random.nextInt(100000)+1);
        JLabel loginL = new JLabel(loginID);

//        JFormattedTextField passwordTextField = new JFormattedTextField();
//        passwordTextField.setColumns(8);

        JPasswordField passwordTextField = new JPasswordField(8);
        JPasswordField passwordCheckText = new JPasswordField(8);







        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new RegistrationWindow.DateLabelFormatter());

//        datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


//        JFormattedTextField textField = datePicker.getJFormattedTextField();
//        System.out.println(textField.getValue());

//        textField.setFormatterFactory(new );
//        datePicker.getJFormattedTextField()


//        Jco

//        title.setForeground(Color.GREEN);


//        title.setSize(200,25);
//        title.setLocation(450, 25);
        nameLabel.setSize(200,25);
        nameLabel.setLocation(30, 20);
        nameTextField.setSize(200,25);
        nameTextField.setLocation(280,20);

        birthdayLabel.setSize(200,25);
        birthdayLabel.setLocation(30,50);
        datePicker.setSize(200,25);
        datePicker.setLocation(280,50);

        phoneLabel.setSize(200,25);
        phoneLabel.setLocation(30,90);
        phoneTextField.setSize(200,25);
        phoneTextField.setLocation(280,90);

        passportIDLabel.setSize(200,25);
        passportIDLabel.setLocation(30,120);
        passportIDTextField.setSize(200,25);
        passportIDTextField.setLocation(280,120);

        loginLabel.setSize(200,25);
        loginLabel.setLocation(30, 150);
        loginL.setSize(200,25);
        loginL.setLocation(280, 150);

        loginPS.setSize(500,25);
        loginPS.setLocation(30,180);

        passwordLabel.setSize(200,25);
        passwordLabel.setLocation(30, 210);
        passwordTextField.setSize(200,25);
        passwordTextField.setLocation(280, 210);

        passwordCheckL.setSize(200,25);
        passwordCheckL.setLocation(30, 240);
        passwordCheckText.setSize(200,25);
        passwordCheckText.setLocation(280, 240);

        signUp.setBounds(400,460,160,25);
        back.setBounds(400,500,160,25);


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }
        });
        this.add(title);
        this.add(back);
        this.add(signUp);
        this.add(nameLabel);
        this.add(nameTextField);
        this.add(birthdayLabel);
        this.add(datePicker);
        this.add(phoneLabel);
        this.add(phoneTextField);
        add(passportIDLabel);
        add(passportIDTextField);
        this.add(loginLabel);
        add(loginPS);
        add(loginL);
        add(passwordLabel);
        add(passwordTextField);
        add(passwordCheckL);
        add(passwordCheckText);



//        b2.setSize(100, 30);
//        b2.setLocation(500, 100);
//        frame.add(b2);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        frame.setContentPane(new BackgroundRegistration());
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
