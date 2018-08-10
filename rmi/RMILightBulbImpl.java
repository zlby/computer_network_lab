import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

// Chapter 11, Listing 2
public class RMILightBulbImpl
	// Extends for remote object functionality
	extends java.rmi.server.UnicastRemoteObject
	// Implements a light bulb RMI interface
	implements RMILightBulb
{
	// A constructor must be provided for the remote object
	public RMILightBulbImpl() throws java.rmi.RemoteException
	{
		// Default value of off
		setBulb(false);
	}

	//new constructor for client
	public RMILightBulbImpl(HashMap<String, User> clients) throws java.rmi.RemoteException
	{
		this.clients=clients;
	}
	
	// Boolean flag to maintain light bulb state information
	private boolean lightOn;

	//infromation for client
	private HashMap<String, User> clients;
	private String username=null;
	private String password=null;

	// Remotely accessible "LOGIN" method
	public boolean login(String name,String pass) throws java.rmi.RemoteException
	{
		if(clients.containsKey(name)){
			if(pass.equals(clients.get(name).getPassword())){
				System.out.println(name+" login in");
				username=name;
				password=pass;
				return true;
			}
			else {
				return false;
			}
		}
		else return false;

	}

	// Remotely accessible "send message"
    public void sendMessage(String s) throws RemoteException {
        // System.out.println(s);
    }

    // Remotely accessible "get message"
    public String getMessage(String text) throws RemoteException {
        return "Your message is: " + text;
    }

    // Remotely accessible "show information"
    public String showInfo(String name, String pass) throws RemoteException{
    	return "Your username is: " + name + "\nYour password is: " + pass + "\n";
    }

	// Remotely accessible "on" method - turns on the light
	public void on() throws java.rmi.RemoteException
	{
		// Turn bulb on
		setBulb (true);
	}

	// Remotely accessible "off" method - turns off the light
	public void off() throws java.rmi.RemoteException
	{
		// Turn bulb off
		setBulb (false);
	}

	// Remotely accessible "isOn" method, returns state of bulb
	public boolean isOn() throws java.rmi.RemoteException
	{
		return getBulb();
	}

	// Locally accessible "setBulb" method, changes state of bulb
	public void setBulb (boolean value)
	{
		lightOn = value;
	}

	// Locally accessible "getBulb" method, returns state of bulb
	public boolean getBulb ()
	{
		return lightOn;
	}
}

