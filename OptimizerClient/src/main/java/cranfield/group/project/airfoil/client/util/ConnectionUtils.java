package cranfield.group.project.airfoil.client.util;

import java.io.IOException;
import java.net.Socket;

public class ConnectionUtils {

	public static boolean checkHostAvailability(String host, int port) {
		try (Socket s = new Socket(host, port)) {
			s.getOutputStream().write(0);
			return true;
		} catch (IOException ex) {
			/* ignore */
		}
		return false;
	}
}
