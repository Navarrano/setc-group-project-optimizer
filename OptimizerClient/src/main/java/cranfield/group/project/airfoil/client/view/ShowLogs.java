package cranfield.group.project.airfoil.client.view;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Kat
 */
public class ShowLogs extends JPanel{
    
    ImageIcon icon = new ImageIcon("src/img/logo.png");
    JLabel logo = new JLabel(icon);
    JTextArea textArea;
    JPanel panelTextField = new JPanel();

    ShowLogs() {

        /*textArea = new JTextArea(20,30);
        //textArea.setColumns(5);
        textArea.setLineWrap(true);
        //textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scrollPane= new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelTextField.add(scrollPane);
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);
        panelTextField.setBorder(blackline);
        //panelTestField.setLayout(new BoxLayout(panelTestField, BoxLayout.Y_AXIS));
        add(panelTextField);*/
        add(logo);
        
      
    }
}