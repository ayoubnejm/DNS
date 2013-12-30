package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sharing.SiteItf;

public class SiteClient {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry();

			SiteItf engine = (SiteItf) registry.lookup("3");

			engine.broadcast(null);
		} catch (RemoteException e) {
		 
			e.printStackTrace();
		} catch (NotBoundException e) {
		 
			e.printStackTrace();
		}
	}
}
