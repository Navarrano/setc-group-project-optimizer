package cranfield.group.project.airfoil.client.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import cranfield.group.project.airfoil.client.MarsClient;

public class DesciptionFrame extends JFrame {

    /*public static void main(String[] args) {
        DesciptionFrame frameTabel = new DesciptionFrame();
    }*/

	private JLabel welcome = new JLabel("About the project");
	private ImageIcon icon = new ImageIcon("img/logo.png");
	private JLabel logo = new JLabel(icon);
	private JPanel panel = new JPanel();
   
	private MarsClient client;

    DesciptionFrame(MarsClient client) {
        super("Welcome");
        
        this.client = client;
        
        setSize(800, 700);
        setLocation(500, 280);
        panel.setLayout(null);
        
        logo.setBounds(10, 10, 150, 81);

        panel.add(welcome);
        panel.add(logo);
        createMenuBar();

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        // Add Listener on the close-window button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	// Terminate the socket connection with the server
                client.terminateConnection();
            }
        });
        
    }
     private void createMenuBar() {
        
       JMenuBar menubar = new JMenuBar();
       
        JMenu iterMenu = new JMenu("New Iteriation");
        JMenu logsMenu = new JMenu("Logs");
        JMenu descripMenu = new JMenu("Description");
       
        menubar.add(iterMenu);
        menubar.add(logsMenu);
        menubar.add(descripMenu);  
       
        menubar.add(Box.createHorizontalGlue());
       

        setJMenuBar(menubar);        
    }

}