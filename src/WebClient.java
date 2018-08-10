/*
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