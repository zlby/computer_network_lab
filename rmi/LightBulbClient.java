import java.rmi.*;
import java.util.*;

// Chapter 11, Listing 4
public class LightBulbClient 
{
	public static void main(String args[])
	{
		System.out.println ("Looking for light bulb service");

		try
		{
			// Check to see if a registry was specified
			String registry = "localhost";
			if (args.length >=1)
			{
				registry = args[0];
			}

			// Registration format //registry_hostname (optional):port /service
			String registration = "rmi://" + registry + "/RMILightBulb";

			// Lookup the service in the registry, and obtain a remote service
			Remote remoteService = Naming.lookup ( registration );
			
			// Cast to a RMILightBulb interface
			RMILightBulb bulbService = (RMILightBulb) remoteService;
	
			// Invoking login method
			System.out.println ("Invoking bulbservice.login()");
			Scanner sb = new Scanner(System.in);
			String username=null,password=null;
			while (true){
				System.out.print("Please input username: ");
				username=sb.nextLine();
				System.out.print("Please input password: ");
				password=sb.nextLine();
				if(!bulbService.login(username,password)){
					System.out.println("Your username or password is wrong please try again");
				}
				else {
					System.out.println("Hello " + username +", you login in successfully");
					boolean flag=true;
					int choose=0;
					while (flag){
						System.out.println("Please input the number you want to choose:");
						System.out.println("1:send message");
						System.out.println("2:check information");
						choose=sb.nextInt();
						switch(choose){
							case 1:
								System.out.println("Please enter the message:");
								String sentence = sb.nextLine();
								bulbService.sendMessage(sentence);
								System.out.println(bulbService.getMessage(sentence));
								break;
							case 2:
								System.out.println(bulbService.showInfo(username, password));
								break;
							default:
								System.out.println("Please input 1 or 2");
						}
					}
					break;
				}
			}
		}
		catch (NotBoundException nbe)
		{
			System.out.println ("No light bulb service available  in registry!");
		}
		catch (RemoteException re)
		{
			System.out.println ("RMI Error - " + re);
		}
		catch (Exception e)
		{
			System.out.println ("Error - " + e);
		}
	}
}

