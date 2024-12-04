package rmi_api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicePersonne extends Remote {
	

	ResponseRMI getAllPersonne() throws RemoteException;
	
	ResponseRMI deletePersonneById(int id) throws RemoteException;
	
	ResponseRMI addPersonne(EntityPersonne p) throws RemoteException;
	
	ResponseRMI updatePersonne(EntityPersonne p) throws RemoteException;
	
}
