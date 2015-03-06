package cranfield.group.project.airfoil.server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import cranfield.group.project.airfoil.server.MarsServer;

public class MainFrame extends JFrame {

	private Thread serverThread;
	private Set<String> connectedUsers = new HashSet<>();

	public MainFrame() {
		super("Server monitoring application");
		setResizable(false);
		initComponents();
		pack();
		setLocationRelativeTo(null);

		serverThread = new Thread(new ServerSocketRunnable());
		serverThread.start();
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

	private class ToggleButtonLister implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (toggleButton.isSelected()) {
				toggleButton.setText("Stop server");
			} else {
				toggleButton.setText("Start server");
			}

		}
	}

	private class ServerSocketRunnable implements Runnable {

		private static final int PORT = 6066;

		private ServerSocket socket;

		@Override
		public void run() {
			try {
				socket = new ServerSocket(PORT);
				System.out.println("SERVER : Waiting for client on port "
						+ socket.getLocalPort() + "...");

				while (true) {
					Socket client = socket.accept();
					System.out.println("SERVER : Just connected to "
							+ client.getRemoteSocketAddress());
					new Thread(new MarsServer(client, connectedUsers)).start();
				}
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
	}

	private final JScrollPane outputPane = new JScrollPane();
	private final JTextArea outputArea = new JTextArea(50, 100);
	private final JToggleButton toggleButton = new JToggleButton("Stop server");
	private final JScrollPane usersPane = new JScrollPane();
	private final JTextArea usersArea = new JTextArea(50, 20);
}
