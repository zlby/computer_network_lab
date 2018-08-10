/*
Created by: Zhou, Liangboya
Date: 2018/5
 */


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class MultiConnectionServer {
    public static void main(String args[]) throws Exception{
        // create a socket at port 6789
        ServerSocket listenSocket = new ServerSocket(6789);

        // the connections and interactions are performed in while loop to get multi-connections
        while (true) {
            Socket connectionSocket = listenSocket.accept();
            String requestMessageLine;
            String fileName;
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            // get request message from client
            requestMessageLine = inFromClient.readLine();

            // dealing with request message
            StringTokenizer tokenizer = new StringTokenizer(requestMessageLine);
            if (tokenizer.nextToken().equals("GET")) {
                fileName = tokenizer.nextToken();
                if (fileName.startsWith("/")) {
                    fileName = fileName.substring(1);
                }
                File file = new File(fileName);
                int numOfBytes = (int) file.length();
                FileInputStream inFile = new FileInputStream(fileName);
                byte[] fileInBytes = new byte[numOfBytes];
                inFile.read(fileInBytes);
                outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\r");
                // judge the type of file requested by client, here we use two examples: jpg file and gif file.
                if (fileName.endsWith(".jpg")) {
                    outToClient.writeBytes("Content-Type: image/jpeg\r\n");
                }
                if (fileName.endsWith(".gif")) {
                    outToClient.writeBytes("Content-Type: image/gif\r\n");
                }
                outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
                outToClient.writeBytes("\r\n");
                outToClient.write(fileInBytes, 0, numOfBytes);
                // flush to make sure
                outToClient.flush();
                //close the connection
                connectionSocket.close();
            } else {
                // if the type of request is not GET, an exception will be throw
                System.out.println("Bad Request Message.");
                connectionSocket.close();
            }

        }
    }
}
