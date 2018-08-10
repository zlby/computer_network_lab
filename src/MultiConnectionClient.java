/*
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