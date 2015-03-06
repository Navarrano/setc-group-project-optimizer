package cranfield.group.project.airfoil.client;

import java.awt.EventQueue;

import cranfield.group.project.airfoil.client.view.AuthenticationFrame;


public class ClientApp
{
    public static void main( String[] args )
    {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				AuthenticationFrame frame = new AuthenticationFrame(
						"localhost", 6065);
				frame.setVisible(true);
			}
		});
    }
}
