package cranfield.group.project.airfoil.client;

import java.awt.EventQueue;

import cranfield.group.project.airfoil.client.view.AuthenticationFrame;


public class ClientApp {

	private static final String HOST = "localhost";
	private static final int PORT = 6066;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				AuthenticationFrame frame = new AuthenticationFrame(HOST, PORT);
				frame.setVisible(true);
			}
		});
	}
}
