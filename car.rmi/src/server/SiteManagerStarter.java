package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sharing.SiteManagerItf;

public class SiteManagerStarter {

	public static void main(String[] args) {
		try {
			// Create and install a security manager
			// if (System.getSecurityManager() == null) {
			// System.setSecurityManager(new RMISecurityManager());
			// }

			SiteManagerItf engine = new SiteManagerImpl("127.0.0.1", 1099);

			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("manager", engine);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
