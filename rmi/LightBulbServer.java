import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.rmi.registry.LocalRegistry;

// Chapter 11, Listing 3
public class LightBulbServer 
{
	public static void main(String args[])
	{
		LocalRegistry.createRegistry(8888);
		HashMap<String, User> clients = new HashMap<String, User>();
		System.out.println ("Loading RMI service");

		try
		{
			User user1=new User("test","123456");
			clients.put(user1.getName(),user1);
			// Load the service
			RMILightBulbImpl bulbService = new RMILightBulbImpl(clients);

			// // Examine the service, to see where it is stored
			// RemoteRef location = bulbService.getRef();
			// System.out.println (location.remoteToString());

			
			// Check to see if a registry was specified
			String registry = "localhost";
			if (args.length >=1)
			{
				registry = args[0];
			}

			// Registration format //registry_hostname (optional):port /service
			String registration = "rmi://" + registry + "/RMILightBulb";
			
			// Register with service so that clients can find us
			Naming.rebind( registration, bulbService );

		}
		catch (RemoteException re)
		{
			System.err.println ("Remote Error - " + re);
		}
		catch (Exception e)
		{
			System.err.println ("Error - " + e);
		}
	}
}