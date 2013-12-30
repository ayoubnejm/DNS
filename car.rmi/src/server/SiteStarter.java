package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SiteStarter {
	public SiteStarter() {
	}

	public void doCustomRmiHandling() {
		try {
			SiteImpl site1 = new SiteImpl("1");
			SiteImpl site2 = new SiteImpl("2");
			SiteImpl site3 = new SiteImpl("3");
			SiteImpl site4 = new SiteImpl("4");
			SiteImpl site5 = new SiteImpl("5");
			SiteImpl site6 = new SiteImpl("6");

			site1.addChildren(site2, site5);
			site2.addChildren(site3, site4);
			site5.addChildren(site6);

			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(site1.getId(), site1);
			registry.rebind(site2.getId(), site2);
			registry.rebind(site3.getId(), site3);
			registry.rebind(site4.getId(), site4);
			registry.rebind(site5.getId(), site5);
			registry.rebind(site6.getId(), site6);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new SiteStarter().doCustomRmiHandling();
	}

}
