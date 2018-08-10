package MessageApp;

import MessageApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.io.*;
import java.util.Scanner;

public class MessageClient {
    public static void main(String args[]){
        try
        {
            // First step : initialize our client ORB

            // Create the ORB
            ORB orb = ORB.init(args, null);

            // Second step: lookup the light bulb using nameservice
            // We use the same code from our servant to create the
            // nameservice reference and namecomponent.

            System.out.println ("Looking for Messenger");

            // Get a name service reference
            org.omg.CORBA.Object object =
                    orb.resolve_initial_references("NameService");

            // Narrow to a NamingContext object
            NamingContext namingContext =
                    NamingContextHelper.narrow(object);

            // Creating a naming component for our light bulb servant
            NameComponent component =
                    new NameComponent ("Messenger", "");

            // NamingContext requires an array, not a single
            // NameComponent
            NameComponent componentList[] = { component };

            // Now, here the client differs. We want to resolve (lookup)
            // the name component representing the light bulb.
            org.omg.CORBA.Object remoteRef =
                    namingContext.resolve(componentList);

            // Next, we narrow it to get an address book
            Message message = MessageHelper.narrow (remoteRef);


            Scanner sb = new Scanner(System.in);
            String username = null, password = null;
            while (true) {
                System.out.print("Please input username: ");
                username = sb.nextLine();
                System.out.print("Please input password: ");
                password = sb.nextLine();
                if (!message.login(username, password)) {
                    System.out.println("Your username or password is wrong please try again");
                } else {
                    System.out.println("Hello " + username + ", you login in successfully");
                    boolean flag = true;
                    int choose = 0;
                    while (flag) {
                        System.out.println("Please input the number you want to choose:");
                        System.out.println("1:send message");
                        System.out.println("2:log out");
                        choose = sb.nextInt();
                        switch (choose) {
                            case 1:
                                System.out.println("Please enter the message:");
                                Scanner sc = new Scanner(System.in);
                                String sentence = sc.nextLine();
                                message.sendMessage(sentence);
                                System.out.println(message.getMessage(sentence));
                                break;
                            case 2:
                                flag = false;
                                break;
                            default:
                                System.out.println("Please input 1 or 2");
                        }
                    }
                    break;
                }
            }




        }
        catch (Exception e)
        {
            System.err.println ("Error - " + e);
        }
    }
}
