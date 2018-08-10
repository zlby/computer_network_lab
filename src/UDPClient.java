import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String args[]) throws Exception {
        // some variables
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        // if the send time is more than 100, an exception will be caught
        clientSocket.setSoTimeout(100);
        int resendTime = 0;
        // get IP address
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String modifiedSentence = "";
        String sentence = "";
        long rtt = 0;
        int flag = 0;
        while (flag != 1) {
            try {
                if (flag == 0) {
                    sentence = inFromUser.readLine();
                }
                else if (flag == -1){
                    ;
                }
                // get the start time
                long startTime = System.currentTimeMillis();
                sendData = sentence.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                modifiedSentence =
                        new String(receivePacket.getData());
                // get the end time
                long endTime = System.currentTimeMillis();
                // calculate rtt
                rtt = endTime - startTime;
                flag = 1;
            } catch (SocketTimeoutException e) {
                // if the data fails to be sent, the resend time will add and send again
                resendTime++;
                flag = -1;
            }
        }

        System.out.println("FROM SERVER: " + modifiedSentence + "\nRTT: " + rtt + "ms" + "\nNumber of resend: " + resendTime);
        clientSocket.close();
    }
}
