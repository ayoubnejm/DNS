package sharing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SiteItf extends Remote {

	public String getId() throws RemoteException;

	public void broadcast(String data) throws RemoteException;

	public void broadcast(String data, List<String> idBroadcasted) throws RemoteException;

	public SiteItf getParent() throws RemoteException;

	public void setParent(SiteItf parent) throws RemoteException;

	public List<SiteItf> getChildren() throws RemoteException;

	public void addChildren(SiteItf... childs) throws RemoteException;

	public void removeParent() throws RemoteException;

	public void removeChild(SiteItf child) throws RemoteException;

	public String getData() throws RemoteException;



}
