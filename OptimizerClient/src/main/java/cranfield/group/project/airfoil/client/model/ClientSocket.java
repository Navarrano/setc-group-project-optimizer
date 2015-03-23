package cranfield.group.project.airfoil.client.model;

import java.io.IOException;
import java.net.Socket;

import cranfield.group.project.airfoil.api.model.OperationType;
import cranfield.group.project.airfoil.client.util.ConnectionUtils;

public class ClientSocket extends Socket {

	public ClientSocket(String serverName, int port) throws IOException {
		super(serverName, port);
	}

	public boolean send(OperationType operation, Object... args)
			throws ServerOfflineException {
		checkServerState();
		return ConnectionUtils.send(this, operation, args);
	}

	public boolean sendArray(OperationType operation, Object[] arr)
			throws ServerOfflineException {
		checkServerState();
		return ConnectionUtils.sendArray(this, operation, arr);
	}

	public boolean send(OperationType operation, Object obj)
			throws ServerOfflineException {
		checkServerState();
		return ConnectionUtils.send(this, operation, obj);
	}

	public boolean send(OperationType operation) throws ServerOfflineException {
		checkServerState();
		return send(operation, (Object) null);
	}

	public boolean send(Object obj) throws ServerOfflineException {
		checkServerState();
		return ConnectionUtils.send(this, obj);
	}

	public OperationType receiveOperation() throws ServerOfflineException {
		checkServerState();
		return ConnectionUtils.receiveOperation(this);
	}

	public <T> T receive(T obj) throws ServerOfflineException {
		checkServerState();
		return ConnectionUtils.receive(this, obj);
	}

	public <T> T receive(Class<T> clazz) throws ServerOfflineException {
		checkServerState();
		return ConnectionUtils.receive(this, clazz);
	}

	private void checkServerState() throws ServerOfflineException {
		if(!ConnectionUtils.checkHostAvailability(getInetAddress()
				.getHostName(), getPort()))
				throw new ServerOfflineException();
	}
}
