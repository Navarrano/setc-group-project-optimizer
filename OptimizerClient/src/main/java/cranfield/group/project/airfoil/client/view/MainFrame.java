package cranfield.group.project.airfoil.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cranfield.group.project.airfoil.api.model.WorkflowDTO;
import cranfield.group.project.airfoil.client.MarsClient;
import cranfield.group.project.airfoil.client.util.ConnectionUtils;

/**
 *
 * @author Kat
 */
public class MainFrame {

    protected JFrame frame = new JFrame("Randomly Selected");
    protected final JPanel contentPane = new JPanel();
    protected ShowLogs showlogs;
    protected MarsClient client;

	protected final String host;
	protected final int port;

	protected final Timer checkServerResponseTimer;
	protected static final int TIMER_PERIOD = 10 * 1000;

	public MainFrame(final MarsClient client, final String host, final int port) {
    	this.client = client;
    	this.host = host;
    	this.port = port;
        contentPane.setLayout(new CardLayout());

        NewIteration newIter = new NewIteration(client);
        contentPane.add(newIter.panelComponent);
        showlogs = new ShowLogs();
        contentPane.add(showlogs.panelComponent,"2");
        DesciptionFrame newDesc = new DesciptionFrame();
        contentPane.add(newDesc.panelComponent);
        createMenuBar();
        frame.add(contentPane);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        actionClosingWindow();
		checkServerResponseTimer = new Timer("CheckServerResponse");
		checkServerResponseTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (!ConnectionUtils.checkHostAvailability(host, port)) {
					client.terminateConnection();
					checkServerResponseTimer.cancel();
					int res = JOptionPane
							.showConfirmDialog(
									frame,
									"Connection with serves has been lost.\nClick OK if you want to return to login frame.",
									"Connection lost",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.ERROR_MESSAGE);
					if (res == JOptionPane.OK_OPTION) {
						AuthenticationFrame frame = new AuthenticationFrame(
								host, port);
						frame.setVisible(true);
					}
					frame.dispose();
				}
			}
		}, 0L, TIMER_PERIOD);
    }

    public ShowLogs getShowLogs(){
    	return showlogs;
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
				checkServerResponseTimer.cancel();
    		}
    	});
    }
    
    class IterActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
			System.out.println("Selected: " + e.getActionCommand());
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