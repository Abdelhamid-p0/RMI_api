package rmi_client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi_api.EntityPersonne;
import rmi_api.ResponseRMI;
import rmi_api.ServicePersonne;


public class Main {
	
	
	   public static void main(String[] args) throws RemoteException, NotBoundException {
	
		System.out.print("Client Running");	
		
		EntityPersonne personne1=new EntityPersonne(1,"karim",12);
		EntityPersonne personne2=new EntityPersonne(2,"Salim",15);
		EntityPersonne personne3=new EntityPersonne(3,"Anas",33);
		
		
		ResponseRMI response = new ResponseRMI();
		
        Registry registry = LocateRegistry.getRegistry("192.168.11.106", 5002);
        
		System.out.print("Client Connected with back en 5002 \n");	

        ServicePersonne service = (ServicePersonne) registry.lookup("ServicePersonne");
        
		System.out.print("Client Service Request : Add Personne" +personne1.name +"\n");	
		
		System.out.println("Back Response : " + service.addPersonne(personne1).body+"\n");
		
		System.out.print("Client Service Request : Add Personne" +personne2.name+"\n");
		
		System.out.println("Back Response : " + service.addPersonne(personne2).body+"\n");
		
		System.out.print("Client Service Request : Add Personne" +personne3.name+"\n");

		System.out.println("Back Response : " + service.addPersonne(personne3).body+"\n");

		
		System.out.print("Client Service Request : get All Personne " +"\n");	

        response = service.getAllPersonne();
        

        System.out.println("Liste des personnes récupérée : " +response.body);
	}

}