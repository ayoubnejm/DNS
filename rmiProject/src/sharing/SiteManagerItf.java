package sharing;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SiteManagerItf extends Remote {

	public void addSite(String pere, String enfant) throws RemoteException;

	public void removeSite(String siteId) throws RemoteException;

}
