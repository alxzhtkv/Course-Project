import javax.swing.*;
import java.awt.*;

public class AdminWindow extends JFrame {
    ClientService clientService;
//    JLabel

    public AdminWindow(ClientService clientService) throws HeadlessException {
        this.clientService = clientService;
    }
}
