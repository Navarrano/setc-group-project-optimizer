package cranfield.group.project.airfoil.server;

import java.awt.EventQueue;

import cranfield.group.project.airfoil.server.gui.MainFrame;

/**
 * Hello world!
 *
 */
public class ServerApp {

	private static final int PORT = 6066;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);
			}
		});
	}
}
