/*
Created by: Zhou, Liangboya
Date: 2018/5
 */


import java.io.*;
import java.net.*;
import java.util.*;

public class WebServer {
    public static void main(String args[]) throws Exception{
        // some variables
        String requestMessageLine;
        String fileName;

        // create a socket at port 6789
        ServerSocket listenSocket = new ServerSocket(6789);
        Socket connectionSocket = listenSocket.accept();

        // get request message from client
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        requestMessageLine = inFromClient.readLine();

        // dealing with request message
        StringTokenizer tokenizer = new StringTokenizer(requestMessageLine);
        if (tokenizer.nextToken().equals("GET")){
            fileName = tokenizer.nextToken();
            if (fileName.startsWith("/")){
                fileName = fileName.substring(1);
            }
            File file = new File(fileName);
            int numOfBytes = (int)file.length();  //the total length of the file
            FileInputStream inFile = new FileInputStream(fileName);
            byte[] fileInBytes = new byte[numOfBytes];  //this fileInBytes stores the file in Bytes
            inFile.read(fileInBytes);
            outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\r");
            // judge the type of file requested by client, here we use two examples: jpg file and gif file.
            if (fileName.endsWith(".jpg")){
                outToClient.writeBytes("Content-Type: image/jpeg\r\n");
            }
            if (fileName.endsWith(".gif")){
                outToClient.writeBytes("Content-Type: image/gif\r\n");
            }
            // generate output to client
            outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
            outToClient.writeBytes("\r\n");
            outToClient.write(fileInBytes, 0, numOfBytes);

            //close connection
            connectionSocket.close();
        }
        else { // if the type of request is not GET, an exception will be throw
            System.out.println("Bad Request Message.");
        }
    }

}
