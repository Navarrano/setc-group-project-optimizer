package cranfield.group.project.airfoil.server;

import cranfield.group.project.airfoil.server.services.EntityFactoryProvider;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManagerFactory;

/**
 * Hello world!
 *
 */
public class ServerApp {

	private static final int PORT = 6066;

	public static void main(String[] args) {
		Set<String> connectedUsers = new HashSet<>();
                EntityManagerFactory emf = EntityFactoryProvider.getInstance().createEntityManagerFactory();
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
                emf.close();
	}
}
