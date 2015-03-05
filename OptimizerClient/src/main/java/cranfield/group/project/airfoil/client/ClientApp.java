package cranfield.group.project.airfoil.client;

import cranfield.group.project.airfoil.client.view.AuthenticationFrame;


public class ClientApp
{
    public static void main( String[] args )
    {
		AuthenticationFrame frameTabel = new AuthenticationFrame("localhost",
				6066);
    }
}
