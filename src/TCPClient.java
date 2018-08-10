import java.io.*;
import java.net.*;

class TCPClient {
public static void main(String argv[]) throws Exception
{
	// some variables
	String sentence;
	String modifiedSentence;
	BufferedReader inFromUser =
		new BufferedReader(new InputStreamReader(System.in));
	// build connections
	Socket clientSocket = new Socket("127.0.0.1", 6789);
	DataOutputStream outToServer =
		new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer =
		new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	// read a user input
	sentence = inFromUser.readLine();
	// start time
    long startTime = System.currentTimeMillis();
	outToServer.writeBytes(sentence + '\n');
	modifiedSentence = inFromServer.readLine();
	// end time
    long endTime = System.currentTimeMillis();

    // calculate rtt
    long rtt = endTime - startTime;
	System.out.println("FROM SERVER: " + modifiedSentence + "\nRTT: " + rtt + "ms");
	clientSocket.close();
	}
}