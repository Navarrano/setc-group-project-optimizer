package cranfield.group.project.airfoil.server;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
	   public static void main(String [] args)
	   {
	      int port = Integer.parseInt(args[0]);
	      try
	      {
	         Thread t = new MarsServer(port);
	         t.start();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	   }
}
