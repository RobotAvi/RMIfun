package com.blogspot.positiveguru;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.*;

/*
* It's just a simple RMI server
*
*/


public class Players {
    private static boolean initiator = true;

    public static void main(String args[]) {
        //Prepare RMI environment
        init();

        if (initiator) {
            doItLikePlayer1();
        } else {
            doItLikePlayer2();
        }

        System.out.println("Bye!");
        System.exit(0);
    }


    private static void init() {

      //  System.setProperty("java.security.policy", "file:///" + Players.class.getClassLoader().getResource("policy").getPath());
        System.out.println("RMI server started");

        // Create and install a security manager
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
            System.out.println("Security manager installed.");
        } else {
            System.out.println("Security manager already exists.");
        }

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            initiator = false;
            System.out.println("java RMI registry already exists.");
        }
    }


    private static void doItLikePlayer1() {
        try {
            //Instantiate RmiServer
            PlayersRemoteInterface obj = new PlayersMessage();

            // Bind this object instance to the name "RmiServer"
            Naming.rebind("//localhost/PlayersChat", obj);

            String msg = "Hello, player!";
            obj.setMessage(msg);
            int countReceive = 0;

            System.out.println("PeerServer bound in registry");

            while (countReceive < 10) {
                if (!msg.equals(obj.getMessage())) {
                    countReceive++;
                    System.out.println("Received msg:" + obj.getMessage());
                    obj.setMessage(msg);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.err.println("RMI server exception:" + e);
            e.printStackTrace();
        }


    }

    private static void doItLikePlayer2() {
        try {
            PlayersRemoteInterface obj = (PlayersRemoteInterface) Naming.lookup("//localhost/PlayersChat");
            int countReceive = 1;
            String message = obj.getMessage();
            obj.setMessage(message + countReceive);
            System.out.println("Receive msg number:" + countReceive);

            while (countReceive < 10) {
                if (obj.getMessage().equals(message)) {
                    countReceive++;
                    System.out.println("Receive msg number:" + countReceive);
                    obj.setMessage(message + countReceive);
                }

            }

        } catch (Exception e) {
            System.err.println("RmiClient exception: " + e);
            e.printStackTrace();

        }


    }
}

