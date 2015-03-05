package cranfield.group.project.airfoil.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import cranfield.group.project.airfoil.client.MarsClient;

/**
 *
 * @author Kat
 */
public class MainFrame {
	
    protected JFrame frame = new JFrame("Randomly Selected");
    protected final JPanel contentPane = new JPanel();
    protected MarsClient client;

    public MainFrame(MarsClient client) {

    	this.client = client;
        contentPane.setLayout(new CardLayout(300, 250));
        
        NewIteration newIter = new NewIteration(client);
        contentPane.add(newIter);
        ShowLogs showlogs = new ShowLogs();
        contentPane.add(showlogs,"2");
        DesciptionFrame newDesc = new DesciptionFrame();
        contentPane.add(newDesc);
        createMenuBar();
        frame.add(contentPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        actionClosingWindow();
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JButton iterMenu = new JButton("New Iteriation");
        JButton logsMenu = new JButton("Logs");
        JButton descripMenu = new JButton("Description");

        iterMenu.setOpaque(false);
        iterMenu.setContentAreaFilled(false);
        iterMenu.setBorderPainted(false);

        logsMenu.setOpaque(false);
        logsMenu.setContentAreaFilled(false);
        logsMenu.setBorderPainted(false);

        descripMenu.setOpaque(false);
        descripMenu.setContentAreaFilled(false);
        descripMenu.setBorderPainted(false);

        menubar.add(iterMenu);
        menubar.add(logsMenu);
        menubar.add(descripMenu);

        menubar.add(Box.createHorizontalGlue());

        iterMenu.addActionListener(new IterActionListener());
        logsMenu.addActionListener(new LogsActionListener());
        descripMenu.addActionListener(new DescripActionListener());
        frame.add(menubar, BorderLayout.PAGE_END);
        frame.setJMenuBar(menubar);
    }

    private void actionClosingWindow(){
    	// Add Listener on the close-window button
    	frame.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) {
    			// Terminate the socket connection with the server
    			client.terminateConnection();
    		}
    	});
    }
    
    class IterActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Selecteddddd: " + e.getActionCommand());
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.first(contentPane);

        }
    }

    class LogsActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "2");
        }
    }

    class DescripActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.last(contentPane);

        }
    }
}