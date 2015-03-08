package cranfield.group.project.airfoil.server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import cranfield.group.project.airfoil.server.MarsServer;
import cranfield.group.project.airfoil.server.models.ConnectedUsers;

public class MainFrame extends JFrame implements Observer {

	private ServerSocketThread serverThread;
	// private Set<String> connectedUsers = new HashSet<>();

	private ConnectedUsers users;

	public MainFrame() {
		super("Server monitoring application");
		setResizable(false);
		initComponents();
		pack();
		setLocationRelativeTo(null);

		serverThread = new ServerSocketThread();
		serverThread.start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add Listener on the close-window button
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Terminate the socket connection with the server
				if (serverThread != null) {
					serverThread.interrupt();
					System.out.println("Thread stopped");
				}
			}
		});
		users = new ConnectedUsers();
		users.addObserver(this);
	}

	private void initComponents() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel buttonPanel = new JPanel();
		toggleButton.setSelected(true);
		toggleButton.addActionListener(new ToggleButtonLister());
		buttonPanel.add(toggleButton);
		mainPanel.add(buttonPanel);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
		outputArea.setEditable(false);
		usersArea.setEditable(false);
		outputPane.getViewport().add(outputArea);
		usersPane.getViewport().add(usersArea);

		textPanel.add(Box.createHorizontalStrut(10));
		textPanel.add(outputPane);
		textPanel.add(Box.createHorizontalStrut(10));
		textPanel.add(usersPane);
		textPanel.add(Box.createHorizontalStrut(10));
		mainPanel.add(textPanel);
		mainPanel.add(Box.createVerticalStrut(10));

		getContentPane().add(mainPanel);
	}

	@Override
	public void update(Observable o, Object arg) {
		usersArea.setText("");
		for (String user : users) {
			usersArea.append(user + "\n");
		}
	}

	private class ToggleButtonLister implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (toggleButton.isSelected()) {
				toggleButton.setText("Stop server");
				serverThread = new ServerSocketThread();
				serverThread.start();
			} else {
				toggleButton.setText("Start server");
				serverThread.interrupt();
				serverThread = null;
			}

		}
	}

	private class ServerSocketThread extends Thread {

		private static final int PORT = 6066;
		private ServerSocket socket;

		public ServerSocketThread() {
			super("Server thread");
		}

		@Override
		public void run() {
			try {
				socket = new ServerSocket(PORT);
				// System.out.println("SERVER : Waiting for client on port "
				// + socket.getLocalPort() + "...");
				outputArea.append("Waiting for client on port "
						+ socket.getLocalPort() + "...\n");

				while (true) {
					Socket client = socket.accept();
					// System.out.println("SERVER : Just connected to "
					// + client.getRemoteSocketAddress());
					outputArea.append("Just connected to "
							+ client.getRemoteSocketAddress() + "\n");
					new Thread(new MarsServer(client, users)).start();
				}
			} catch (SocketException e) {
				// DO NOTHING
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

			}
		}

		@Override
		public void interrupt() {
			super.interrupt();
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
					System.out.println("SERVER: Socket has been closed");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private final JScrollPane outputPane = new JScrollPane();
	private final JTextArea outputArea = new JTextArea(50, 100);
	private final JToggleButton toggleButton = new JToggleButton("Stop server");
	private final JScrollPane usersPane = new JScrollPane();
	private final JTextArea usersArea = new JTextArea(50, 20);
}
