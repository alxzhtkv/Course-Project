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

public class RegistrationWindow extends JFrame  {
    public RegistrationWindow(){
        JFrame frame = new JFrame();
        frame.setSize(1000,600);
        frame.setLayout(null);
        JButton back= new JButton("Назад");
        JButton signUp = new JButton("Зарегистрироваться");
        JLabel title = new JLabel("Регистрация");
        JLabel nameLabel = new JLabel("Ваше ФИО: ");
        JLabel phoneLabel = new JLabel("Контактный телефон: ");
        JLabel birthdayLabel = new JLabel("Дата вашего рождения: ");
        JLabel loginLabel = new JLabel("login: ");
        JLabel passwordLabel = new JLabel("password: ");

        JTextField nameTextField =new JTextField();
        JFormattedTextField phoneTextField = null;
        try {
            phoneTextField = new JFormattedTextField(
                    new MaskFormatter("+375-##-###-##-##"));
            phoneTextField.setColumns(12);
        }catch (ParseException parseException){
            parseException.printStackTrace();
        }





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

//        loginLabel.setSize(200,25);
//        loginLabel.setLocation(200, 200);
//        passwordLabel.setSize(200,40);
//        passwordLabel.setLocation(500, 300);

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
        frame.add(nameLabel);
        frame.add(nameTextField);
        frame.add(birthdayLabel);
        frame.add(datePicker);
        frame.add(phoneLabel);
        frame.add(phoneTextField);



//        b2.setSize(100, 30);
//        b2.setLocation(500, 100);
//        frame.add(b2);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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
