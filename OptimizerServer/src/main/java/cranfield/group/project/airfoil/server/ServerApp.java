package cranfield.group.project.airfoil.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class ServerApp {

	private static final int PORT = 6066;

	public static void main(String[] args) {
		Set<String> connectedUsers = new HashSet<>();
		ServerSocket socket = null;
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
