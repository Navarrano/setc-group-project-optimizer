package cranfield.group.project.airfoil.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cranfield.group.project.airfoil.client.MarsClient;

@SuppressWarnings("serial")
public class AuthenticationFrame extends JFrame {

	private MarsClient client;

	private final JPanel panel = new JPanel();
	private final JButton loginButton = new JButton("Login");
	private final JTextField txuser = new JTextField(20);
	private final JPasswordField pass = new JPasswordField(20);
	JLabel errorLabel = new JLabel(" ");

	private static final String errorMsg = "Unable to connect with server! Click here to change server URL";
	private String host;
	private int port;

	public AuthenticationFrame(String host, int port) {
		super("Login Authentication");
		this.host = host;
		this.port = port;

		setSize(500, 150);
		setLocationRelativeTo(null);
		setResizable(false);
		// setLocation(500, 280);

		initComponents();
		initConnection(host, port);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add Listener on the close-window button
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Terminate the socket connection with the server
				if (client != null && client.isConnected())
					client.terminateConnection();
			}
		});
		pack();
	}

	private void initConnection(String host, int port) {
		try {
			client = new MarsClient(host, port);
			loginButton.setEnabled(true);
			errorLabel.setText(" ");
		} catch (IOException e) {
			loginButton.setEnabled(false);
			errorLabel.setText(errorMsg);
		}
	}

	private void initComponents() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(10));

		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(errorLabel.getFont().deriveFont(Font.BOLD));
		errorLabel.addMouseListener(new ErrorLabelMouseAdapter(this));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(errorLabel);
		panel.add(Box.createVerticalStrut(10));

		JPanel firstRow = new JPanel();
		firstRow.setLayout(new BoxLayout(firstRow, BoxLayout.X_AXIS));
		firstRow.add(Box.createHorizontalStrut(20));
		firstRow.add(new JLabel("Username: "));
		firstRow.add(txuser);
		firstRow.add(Box.createHorizontalStrut(20));
		panel.add(firstRow);
		panel.add(Box.createVerticalStrut(10));

		JPanel secondRow = new JPanel();
		secondRow.setLayout(new BoxLayout(secondRow, BoxLayout.X_AXIS));
		secondRow.add(Box.createHorizontalStrut(20));
		secondRow.add(new JLabel("Password: "));

		pass.addKeyListener(new PasswordKeyAdapter());

		secondRow.add(pass);
		secondRow.add(Box.createHorizontalStrut(20));
		panel.add(secondRow);
		panel.add(Box.createVerticalStrut(10));

		loginButton.addActionListener(new LoginButtonLister());
		loginButton.setAlignmentX(CENTER_ALIGNMENT);
		loginButton.setEnabled(false);
		panel.add(loginButton);
		panel.add(Box.createVerticalStrut(10));

		getContentPane().add(Box.createHorizontalStrut(50),
				BorderLayout.LINE_START);
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(Box.createHorizontalStrut(50),
				BorderLayout.LINE_END);
	}

	private class PasswordKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (loginButton.isEnabled())
					loginButton.doClick();
			}
		}
	}

	private class ErrorLabelMouseAdapter extends MouseAdapter {

		private Component parent;

		public ErrorLabelMouseAdapter(Component parent) {
			this.parent = parent;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			JPanel inputPanel = new JPanel();
			JTextField hostField = new JTextField(host, 25);
			JTextField portField = new JTextField(String.valueOf(port), 25);

			inputPanel.add(new JLabel("Host:"));
			inputPanel.add(hostField);
			inputPanel.add(new JLabel("Port:"));
			inputPanel.add(portField);

			int result = JOptionPane.showConfirmDialog(parent, inputPanel,
					"Server connection", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				host = hostField.getText();
				try {
					port = Integer.parseInt(portField.getText());
					initConnection(host, port);
				} catch (NumberFormatException ex) {
					errorLabel.setText("Port must be a number");
				}
			}
		}
	}

	private class LoginButtonLister implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String puname = txuser.getText();
			char[] password = pass.getPassword();
			String ppaswd = new String(password);
			password = null;

			String msg = client.areValidatedCredentials(puname, ppaswd);
			if (msg == null) {
				MainFrame mainFrame = new MainFrame(client);
				client.addObserver(mainFrame.getShowLogs());
				client.notifyObservers("Connected to Server @" + client.getClientSocket().getRemoteSocketAddress());

				dispose();
			} else {
				JOptionPane.showMessageDialog(null, msg);
				txuser.setText("");
				pass.setText("");
				txuser.requestFocus();
			}
		}
	}

}
