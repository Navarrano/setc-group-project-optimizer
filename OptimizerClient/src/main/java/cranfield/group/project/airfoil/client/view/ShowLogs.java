package cranfield.group.project.airfoil.client.view;

import javax.swing.*;

/**
 *
 * @author Kat
 */
public class ShowLogs extends JPanel{
    
    protected ImageIcon icon = new ImageIcon("src/img/logo.png");
    protected JLabel logo = new JLabel(icon);
    protected JTextArea textAreaLogs;
    protected JLabel labelLogs = new JLabel("Logs: ");
    protected JTextArea textAreaList;
    protected JLabel labelList = new JLabel("List: ");
    protected JPanel panelComponent = new JPanel();

    ShowLogs() {

       textAreaLogs = new JTextArea(12, 40);
        textAreaLogs.setEditable(false);
        JScrollPane scrollPaneLogs = new JScrollPane(textAreaLogs);
        scrollPaneLogs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneLogs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textAreaList = new JTextArea(12, 40);
        textAreaList.setEditable(false);
        JScrollPane scrollPaneList = new JScrollPane(textAreaList);
        scrollPaneList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelComponent.setLayout(new BoxLayout(panelComponent, BoxLayout.Y_AXIS));
        panelComponent.add(labelLogs);
        panelComponent.add(scrollPaneLogs);
        panelComponent.add(labelList);
        panelComponent.add(scrollPaneList);
    }
}