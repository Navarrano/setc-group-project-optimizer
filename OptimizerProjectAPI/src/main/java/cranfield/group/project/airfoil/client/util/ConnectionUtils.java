package cranfield.group.project.airfoil.client.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cranfield.group.project.airfoil.api.model.OperationType;

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

	public static boolean send(Socket socket, OperationType operation,
			Object... args) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					socket.getOutputStream());
			out.writeObject(operation);
			for (Object obj : args) {
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(obj);
			}

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean sendArray(Socket socket, OperationType operation,
			Object[] arr) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					socket.getOutputStream());
			out.writeObject(operation);
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(arr);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean send(Socket socket, OperationType operation,
			Object obj) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					socket.getOutputStream());
			out.writeObject(operation);
			out = new ObjectOutputStream(socket.getOutputStream());
			if (obj != null)
				out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean send(Socket socket, OperationType operation) {
		return send(socket, operation, (Object) null);
	}

	public static boolean send(Socket socket, Object obj) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					socket.getOutputStream());
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static OperationType receiveOperation(Socket socket) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					socket.getInputStream());
			return (OperationType) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T receive(Socket socket, T obj) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					socket.getInputStream());
			return (T) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T receive(Socket socket, Class<T> clazz) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					socket.getInputStream());
			return (T) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
