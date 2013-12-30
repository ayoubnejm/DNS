package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import sharing.SiteItf;

public class SiteImpl extends UnicastRemoteObject implements SiteItf {

	private static final long serialVersionUID = 1L;

	private List<SiteItf> children;
	private String id;
	private SiteItf parent;

	private String data;

	public SiteImpl(String id) throws RemoteException {
		super();
		this.id = id;
		this.children = new ArrayList<SiteItf>();
		this.parent = null;
		this.data = "";
	}

	public void setParent(SiteItf newParent) throws RemoteException {
		if (this.parent == null) {
			this.parent = newParent;
			this.parent.addChildren(this);
		} else {
			// If the parent already existe and isn't the same as the new
			// parent: tell the old parent to remove the link
			if (!this.parent.equals(newParent)) {
				this.parent.removeChild(this);
				this.parent = newParent;
				this.parent.addChildren(this);
			}
		}
	}

	public void addChildren(SiteItf... childs) throws RemoteException {
		for (SiteItf child : childs) {
			// In order to avoid looping
			if (!this.children.contains(child)) {
				this.children.add(child);
				child.setParent(this);
			}
		}
	}

	public synchronized void broadcast(final String data, final List<String> idBroadcasted) throws RemoteException {
		// If already sent data => do nothing
		if (idBroadcasted.contains(this.id))
			return;

		System.out.println(data);

		idBroadcasted.add(this.id);
		// If not root: tell the parent to transfert
		if (this.parent != null) {
			new Thread() {

				public void run() {
					try {
						parent.broadcast(data, idBroadcasted);
					} catch (RemoteException e) {
					}
				}
			}.start();
		}

		// Transfert to every child
		for (final SiteItf child : this.children) {
			new Thread() {

				public void run() {
					try {
						child.broadcast(data, idBroadcasted);
					} catch (RemoteException e) {
					}
				}
			}.start();
		}

	}

	public void broadcast(final String data) throws RemoteException {
		this.broadcast(data, new ArrayList<String>());
	}

	public String getId() throws RemoteException {
		return this.id;
	}

	public boolean equals(Object obj) {
		if (obj instanceof SiteItf) {
			SiteItf aSite = (SiteItf) obj;
			try {
				return aSite.getId().equals(this.getId());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return super.equals(obj);

	}

	public List<SiteItf> getChildren() throws RemoteException {
		return children;
	}

	public SiteItf getParent() throws RemoteException {
		return parent;
	}

	public int hashCode() {
		return this.id.hashCode();
	}

	public void removeParent() throws RemoteException {
		if (this.parent != null) {
			this.parent.removeChild(this);
			this.parent = null;
		}
	}

	public void removeChild(SiteItf child) throws RemoteException {
		// Remove the child form children and tell the child to remove parent
		if (this.children.remove(child))
			child.removeParent();
	}

	public String getData() throws RemoteException {
		return this.data;
	}

}
