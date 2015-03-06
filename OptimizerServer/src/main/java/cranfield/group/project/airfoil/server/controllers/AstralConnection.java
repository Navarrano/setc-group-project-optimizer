package cranfield.group.project.airfoil.server.controllers;

import java.net.UnknownHostException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class AstralConnection implements AutoCloseable {

	private final Session session;

	private final static String HOST = "astral.central.cranfield.ac.uk";
	private final static int PORT = 22;

	public AstralConnection(String username, String password)
			throws JSchException {
		JSch ssh = new JSch();

		session = ssh.getSession(username, HOST, PORT);
		session.setConfig("MaxAuthTries", "1");

		session.setUserInfo(new AstralUserInfo(password));

		try {
			session.connect();
		} catch (JSchException e) {
			if (e.getCause() instanceof UnknownHostException) {
				throw new JSchException("Unable to connect to server");
			} else if (e.getMessage().equals("Auth fail"))
				throw new JSchException("Invalid username or password");
			else
				throw e;
		}
	}

	@Override
	public void close() {
		if (session != null && session.isConnected())
			session.disconnect();
	}

	private class AstralUserInfo implements UserInfo, UIKeyboardInteractive {

		private final String password;

		public AstralUserInfo(String password) {
			this.password = password;
		}

		@Override
		public String[] promptKeyboardInteractive(String destination,
				String name, String instruction, String[] prompt, boolean[] echo) {
			String[] response = new String[prompt.length];

			for (int i = 0; i < prompt.length; i++) {
				if (prompt[i].startsWith("Password:"))
					response[i] = password;
				else
					response[i] = "";
			}

			return response;
		}

		@Override
		public String getPassphrase() {
			return null;
		}

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public boolean promptPassword(String message) {
			return true;
		}

		@Override
		public boolean promptPassphrase(String message) {
			return false;
		}

		@Override
		public boolean promptYesNo(String message) {
			return true;
		}

		@Override
		public void showMessage(String message) {
		}
	}
}
