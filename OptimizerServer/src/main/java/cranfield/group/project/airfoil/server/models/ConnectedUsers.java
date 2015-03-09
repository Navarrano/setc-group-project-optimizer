package cranfield.group.project.airfoil.server.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

public class ConnectedUsers extends Observable implements Iterable<String> {

	private Set<String> connectedUsers = new HashSet<>();

	public void add(String user) {
		connectedUsers.add(user);
		setChanged();
		notifyObservers();
	}


	public void remove(String user) {
		connectedUsers.remove(user);
		setChanged();
		notifyObservers(user);
	}

	public boolean contains(String user) {
		return connectedUsers.contains(user);
	}

	@Override
	public Iterator<String> iterator() {
		return connectedUsers.iterator();
	}
}
