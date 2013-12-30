package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import sharing.SiteItf;
import sharing.SiteManagerItf;

public class SiteManagerImpl extends UnicastRemoteObject implements SiteManagerItf {

	private static final long serialVersionUID = 1L;
	private String registryHost;
	private int registryPort;

	public SiteManagerImpl() throws RemoteException {
		super();
	}

	public void addSite(String pere, String enfant) {
		boolean ajoute = true;

		try {

			SiteItf p = (SiteItf) Naming.lookup(pere);
			SiteItf f = (SiteItf) Naming.lookup(enfant);
			p.addChildren(f);
		} catch (MalformedURLException e) {
			ajoute = false;
			e.printStackTrace();
		} catch (RemoteException e) {
			ajoute = false;
			e.printStackTrace();
		} catch (NotBoundException e) {
			ajoute = false;
			e.printStackTrace();
		}

		if (!ajoute)
			System.out.println(enfant + " ne peut etre ajoute a " + pere);

	}

	public void removeSite(String siteId) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(this.registryHost,
					this.registryPort);
			// Site id cannot be empty
			if (siteId != null && !siteId.isEmpty()) {

				SiteItf site;

				try {

					site = (SiteItf) registry.lookup(siteId);

					// Remove all links of the site
					site.removeParent();
					for (SiteItf child : site.getChildren()) {
						site.removeChild(child);
					}

					registry.unbind(siteId);

				} catch (NotBoundException nbe) {

				}

			}

		} catch (RemoteException re) {
		}
	}
}
