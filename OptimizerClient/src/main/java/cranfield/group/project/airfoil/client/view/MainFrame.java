package cranfield.group.project.airfoil.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cranfield.group.project.airfoil.client.MarsClient;
import cranfield.group.project.airfoil.client.util.ConnectionUtils;

/**
 *
 * @author Kat
 */
public class MainFrame extends JFrame {

    protected final JPanel contentPane = new JPanel();
    protected ShowLogs showlogs;
    protected MarsClient client;

    protected final String host;
    protected final int port;

    protected final Timer checkServerResponseTimer;
	protected static final int TIMER_PERIOD = 60 * 1000;

    public MainFrame(final MarsClient client, final String host, final int port) {
		super("Randomly Selected");
        this.client = client;
        this.host = host;
        this.port = port;
        contentPane.setLayout(new CardLayout());

		NewIteration newIter = new NewIteration(this, client);
		contentPane.add(newIter.panelComponent, CardType.ITERATION.name());
        showlogs = new ShowLogs();
		contentPane.add(showlogs.panelComponent, CardType.LOGS.name());
        DesciptionFrame newDesc = new DesciptionFrame();
		contentPane.add(newDesc.panelComponent, CardType.DESCRIPTION.name());
        createMenuBar();
        add(contentPane);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        actionClosingWindow();
        checkServerResponseTimer = new Timer("CheckServerResponse");
        checkServerResponseTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (!ConnectionUtils.checkHostAvailability(host, port)) {
					closeAfterDisconnection();
                }
            }
        }, 0L, TIMER_PERIOD);
    }

	public void closeAfterDisconnection() {
		client.terminateConnection();

		checkServerResponseTimer.cancel();
		int res = JOptionPane
				.showConfirmDialog(
						null,
						"Connection with serves has been lost.\nClick OK if you want to return to login frame.",
						"Connection lost", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.ERROR_MESSAGE);
		if (res == JOptionPane.OK_OPTION) {
			AuthenticationFrame frame = new AuthenticationFrame(host, port);
			frame.setVisible(true);
		}
		dispose();
	}

    public ShowLogs getShowLogs() {
        return showlogs;
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JButton iterMenu = new JButton("Optimizer");
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

		iterMenu.addActionListener(new ChangeCardListener(CardType.ITERATION));
		logsMenu.addActionListener(new ChangeCardListener(CardType.LOGS));
		descripMenu.addActionListener(new ChangeCardListener(
				CardType.DESCRIPTION));
        add(menubar, BorderLayout.PAGE_END);
        setJMenuBar(menubar);
    }

    private void actionClosingWindow() {
        // Add Listener on the close-window button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Terminate the socket connection with the server
				client.terminateConnection();

                checkServerResponseTimer.cancel();
            }
        });
    }

	private enum CardType {
		ITERATION, LOGS, DESCRIPTION;
	}

	private class ChangeCardListener implements ActionListener {

		private final CardType cardType;

		public ChangeCardListener(CardType cardType) {
			this.cardType = cardType;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) contentPane.getLayout();
			cardLayout.show(contentPane, cardType.name());
		}

	}

	// class IterActionListener implements ActionListener {
	//
	// public void actionPerformed(ActionEvent e) {
	// CardLayout cardLayout = (CardLayout) contentPane.getLayout();
	// cardLayout.first(contentPane);
	//
	// }
	// }
	//
	// class LogsActionListener implements ActionListener {
	//
	// public void actionPerformed(ActionEvent e) {
	// CardLayout cardLayout = (CardLayout) contentPane.getLayout();
	// cardLayout.show(contentPane, "2");
	// }
	// }
	//
	// class DescripActionListener implements ActionListener {
	//
	// public void actionPerformed(ActionEvent e) {
	// CardLayout cardLayout = (CardLayout) contentPane.getLayout();
	// cardLayout.last(contentPane);
	//
	// }
	// }
}
