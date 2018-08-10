import java.io.*;
import java.net.*;

class TCPServer {
public static void main(String argv[]) throws Exception
{
	// some variables
	String clientSentence;
	String capitalizedSentence;
	ServerSocket welcomeSocket = new ServerSocket(6789);
	// loop to get data from client
	while(true) {
		// build connection socket
	    Socket connectionSocket = welcomeSocket.accept();
		BufferedReader inFromClient =
		new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = 
		new DataOutputStream(connectionSocket.getOutputStream());
		clientSentence = inFromClient.readLine();
		capitalizedSentence = clientSentence.toUpperCase() + '\n';
		outToClient.writeBytes(capitalizedSentence);
		}
	}
}