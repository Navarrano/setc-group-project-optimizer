package cranfield.group.project.airfoil.client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

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
	JLabel errorLabel = new JLabel("Unable to connect with server!");

	private String host;
	private int port;

	public AuthenticationFrame(String host, int port) {
		super("Login Authentication");
		this.host = host;
		this.port = port;

		setSize(400, 200);
		setLocation(500, 280);

		initComponents();
		initConnection(host, port);

		getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add Listener on the close-window button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	// Terminate the socket connection with the server
				if (client != null && client.isConnected())
					client.terminateConnection();
            }
        });
    }

	private void initConnection(String host, int port) {
		try {
			client = new MarsClient(host, port);
			loginButton.setEnabled(true);
			errorLabel.setVisible(false);
		} catch (IOException e) {
			loginButton.setEnabled(false);
			errorLabel.setVisible(true);
		}
	}

	private void initComponents() {
		panel.setLayout(null);

		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		errorLabel.addMouseListener(new ErrorLabelMouseAdapter(this));


		loginButton.addActionListener(new LoginButtonLister());

		errorLabel.setBounds(55, 5, 180, 20);
		txuser.setBounds(70, 30, 150, 20);
		pass.setBounds(70, 65, 150, 20);
		loginButton.setBounds(110, 100, 80, 20);

		panel.add(errorLabel);
		panel.add(loginButton);
		panel.add(txuser);
		panel.add(pass);

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
					initConnection(host, result);
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
			if (client.areValidatedCredentials(puname, ppaswd)) {
				DesciptionFrame regFace = new DesciptionFrame(client);
				regFace.setVisible(true);
				dispose();
			} else {
				JOptionPane
						.showMessageDialog(null, "Wrong Password / Username");
				txuser.setText("");
				pass.setText("");
				txuser.requestFocus();
            }
		}
	}

}
