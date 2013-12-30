package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
  
import sharing.*;

import server.*;
public class Client {
	/**
     * @param args
     * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
     */
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
    	SiteManagerImpl manager = new SiteManagerImpl();
    	manager.addSite("racine", "2");
    	manager.addSite("racine", "5");
    	manager.addSite("2", "3");
    	manager.addSite("2", "4");
    	manager.addSite("5", "6");

        SiteItf element = (SiteItf)Naming.lookup("racine");
        element.broadcast("Message envoyé a toutes les noeuds!!");
        String message;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
			try {
				message = br.readLine();
				element.broadcast(message);
			} catch (IOException ioe) {
				System.out.println("IO error trying to read message!");
				System.exit(1);
			}
			System.out.print("message sent successfully\n> ");
		}
           
       	
    }

   
}