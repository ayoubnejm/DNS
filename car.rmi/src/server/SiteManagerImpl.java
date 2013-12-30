package server;

import java.rmi.AlreadyBoundException;
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

	public SiteManagerImpl(String registryHost, int registryPort)
			throws RemoteException {
		super();
		this.registryHost = registryHost;
		this.registryPort = registryPort;
	}

	 
	public void addSite(String siteId) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(this.registryHost,
					this.registryPort);
			// Site id cannot be empty
			if (siteId != null && !siteId.isEmpty()) {
				SiteItf site;

				try {

					site = (SiteItf) registry.lookup(siteId);
					// Site already created and bound so do nothing

				} catch (NotBoundException nbe) {

					// Create new site
					site = new SiteImpl(siteId);
					try {
						registry.bind(siteId, site);
					} catch (AlreadyBoundException abe) {
						// Something is not right
						abe.printStackTrace();
					}

				}
			}

		} catch (RemoteException re) {
		}
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
