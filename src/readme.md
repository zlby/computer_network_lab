# Attachment

* name: 周梁博雅
* id: 2015302580216

### Description

There are totally 4 java files: WebClient.java, WebServer.java for Lab 1, and MultiConnectionClient.java, MultiConnectionServer.java for Lab 2.

![1529000874117](C:\Users\周梁博雅\AppData\Local\Temp\1529000874117.png)

There is also a jpg file for testing connections between clients and servers.

### Code

```java
/*
WebServer.java
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

```

```java
/*
WebClient.java
Created by: Zhou, Liangboya
Date: 2018/5
 */


import java.io.*;
import java.net.*;


class WebClient {
    public static void main(String argv[]) throws Exception
    {
        // some variables
        String fileName;
        String httpRequest;
        String modifiedSentence;
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        // create a socket at port 6789
        Socket clientSocket = new Socket("127.0.0.1", 6789);
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // here at client side, a input message is required (file name)
        System.out.println("Please enter the filename: \n");
        fileName = inFromUser.readLine();

        // generate the input file name into a http request
        httpRequest = "GET " + fileName;
        outToServer.writeBytes(httpRequest + "\n");
        String outPutSentence = "";

        // get response from server
        modifiedSentence = inFromServer.readLine();
        while(modifiedSentence != null){
            outPutSentence = outPutSentence + "\n" + modifiedSentence;
            modifiedSentence = inFromServer.readLine();
        }

        // show the output received from server side in command window
        System.out.println("FROM SERVER: " + outPutSentence);

        // close the connection socket
        clientSocket.close();

    }
}
```

```java
/*
MultiConnectionServer.java
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

```

```java
/*
MultiConnectionClient.java
Created by: Zhou, Liangboya
Date: 2018/5
 */


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class MultiConnectionClient {
    public static void main(String argv[]) throws Exception {
        // these three variables stores number of connections, input flow and output flow
        int numOfConnections = 0;
        int inputflow = 0;
        int outputflow = 0;

        // the connections and interactions are performed in while loop to get multi-connections
        while(true) {
            // some variables
            String fileName;
            String httpRequest;
            String modifiedSentence;
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            // create a socket at port 6789
            Socket clientSocket = new Socket("127.0.0.1", 6789);
            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // here at client side, a input message is required (file name)
            System.out.println("Please enter the filename: \n");
            fileName = inFromUser.readLine();

            // if we enter "end" instead of file name, the loop will end
            if (fileName.equals("end")){
                clientSocket.close();
                break;
            }

            // generate the input file name into a http request
            httpRequest = "GET " + fileName;
            outToServer.writeBytes(httpRequest + "\n");
            String outPutSentence = "";
            modifiedSentence = inFromServer.readLine();
            while (modifiedSentence != null) {
                outPutSentence = outPutSentence + "\n" + modifiedSentence;
                modifiedSentence = inFromServer.readLine();
            }

            // sum up the parameters
            numOfConnections ++;
            inputflow += outPutSentence.getBytes().length;
            outputflow += httpRequest.getBytes().length;
            //close connection
            clientSocket.close();
        }

        // show the output received from server side in command window
        System.out.println();
        System.out.println("Number Of Connections: " + numOfConnections);
        System.out.println("Input Flow: " + inputflow);
        System.out.println("Output Flow: " + outputflow);
    }
}
```

### Usage

##### Lab 1

For Lab 1, we should run WebServer and then WebClient,  then there is a reminder to let user enter a file name at server side. Here we use file "a.jpg" for example, here is the example output:

![1529001123310](C:\Users\周梁博雅\AppData\Local\Temp\1529001123310.png)

Because the content of the file is a jpg image, so there are some messy code. If we open it in browser, we will see the picture.

##### Lab 2

For Lab 1, we should run MultiConnectionServer and then MultiConnectionClient, then we can enter file name many many times, for each time a connection will be built: 

![1529001318724](C:\Users\周梁博雅\AppData\Local\Temp\1529001318724.png)

Here we build 4 connections for example. Then we enter end to terminate and calculate the total parameters:

![1529001380439](C:\Users\周梁博雅\AppData\Local\Temp\1529001380439.png)

 



