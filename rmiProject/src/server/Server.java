package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

		SiteImpl element;
		System.setSecurityManager(new RMISecurityManager());
		java.security.Policy.getPolicy();
		if (args.length > 0) {
			element = new SiteImpl(args[0]);
			Registry r = LocateRegistry.getRegistry("localhost", 1099);
			r.rebind(args[0], element);
			System.out.println(args[0] + " a ete cree");
		} else {

			element = new SiteImpl("racine");
			Registry r = LocateRegistry.getRegistry("localhost", 1099);
			r.bind("racine", element);
			System.out.println("racine a ete cree");
		}

		System.out.println("Serveur pret a etre utilise...");

		String message;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
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