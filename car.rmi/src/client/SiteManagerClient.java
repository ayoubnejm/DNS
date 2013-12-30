package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sharing.SiteItf;
import sharing.SiteManagerItf;

public class SiteManagerClient {

	public static void main(String[] args) {
		try {
			Registry regitry = LocateRegistry.getRegistry();
			SiteManagerItf siteManager = (SiteManagerItf) regitry
					.lookup("manager");
			siteManager.addSite("1");
			siteManager.addSite("2");
			siteManager.addSite("3");
			siteManager.addSite("4");
			siteManager.addSite("5");
			siteManager.addSite("6");

			SiteItf site1 = (SiteItf) regitry.lookup("1");
			SiteItf site2 = (SiteItf) regitry.lookup("2");
			SiteItf site3 = (SiteItf) regitry.lookup("3");

			site2.addChildren(site3);
			site2.setParent(site1);
			site2.removeChild(site3);

			site1.broadcast("a");

		} catch (RemoteException e) {
		 
			e.printStackTrace();
		} catch (NotBoundException e) {
			 
			e.printStackTrace();
		}
	}
}
