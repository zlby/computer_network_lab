// Chapter 11, Listing 1
public interface RMILightBulb extends java.rmi.Remote
{
	public void on ()     throws java.rmi.RemoteException;
	public void off()     throws java.rmi.RemoteException;
	public boolean isOn() throws java.rmi.RemoteException;

	public boolean login(String name,String pass) throws java.rmi.RemoteException;
	public void sendMessage(String s) throws java.rmi.RemoteException;
	public String getMessage(String s) throws java.rmi.RemoteException;
	public String showInfo(String name, String pass) throws java.rmi.RemoteException;
}

