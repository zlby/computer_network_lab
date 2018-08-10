package MessageApp;

import MessageApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import MessageApp.User;

import java.io.*;
import java.util.HashMap;

public class MessageServant extends MessagePOA {
    private HashMap<String, User> clients;

    public MessageServant() {
        this.clients = new HashMap<>();
        User user1 = new User("test", "123456");
        this.clients.put(user1.getName(), user1);
    }
    public static void main(String args[]) {
        System.out.println("Loading ORB");


        try {
            // Create the ORB
            ORB orb = ORB.init(args, null);

            // Create a new light bulb servant ...
            MessageServant servant = new MessageServant();

            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            MessageServant messageServant = new MessageServant();


            org.omg.CORBA.Object ref = rootPoa.servant_to_reference(messageServant);
            Message iref = MessageHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);


            String name = "Message";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, iref);

            System.out.println("Message server ready...");


            orb.run();
        } catch (Exception e) {
            System.err.println("Error - " + e);
        }

    }

    @Override
    public String getMessage(String s) {
        return "Your Message is: " + s;
    }

    @Override
    public void sendMessage(String s) {
        System.out.println(s);
    }

    @Override
    public boolean login(String name, String pass) {
        if (clients.containsKey(name)) {
            if (pass.equals(clients.get(name).getPassword())) {
                System.out.println(name + " login in");
                return true;
            } else {
                return false;
            }
        } else return false;
    }
}
