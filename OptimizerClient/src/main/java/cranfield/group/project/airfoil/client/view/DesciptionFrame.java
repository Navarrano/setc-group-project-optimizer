package cranfield.group.project.airfoil.client.view;

import java.awt.GridLayout;
import javax.swing.*;

public class DesciptionFrame extends JPanel {

    //ImageIcon icon = new ImageIcon("src/img/logo.png");
    JLabel logo = new JLabel("icon");

    DesciptionFrame() {
        setLayout(new GridLayout(0, 1));
        //logo.setBounds(10, 10, 150, 81);
        //logo.setVisible(true);
        add(logo);
        //setVisible(true);
        //setLayout(null);
    }

}