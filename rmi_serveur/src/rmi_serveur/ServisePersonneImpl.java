package rmi_serveur;

import java.util.ArrayList;
import java.util.List;

import rmi_api.EntityPersonne;
import rmi_api.ResponseRMI;
import rmi_api.ServicePersonne;

public class ServisePersonneImpl implements ServicePersonne {
	
	List<EntityPersonne> listePersonnes = new ArrayList<EntityPersonne>();

	@Override
	public String toString() {
		return "[listePersonnes=" + listePersonnes + "]";
	}
	
	@Override
	public ResponseRMI getAllPersonne() {
		
		System.out.print("Client access to getAllPersonne()"+"\n");	

		ResponseRMI responseRMI =new ResponseRMI("404", this.toString());
		
		System.out.print("Service successfully done"+"\n");	
		
		return responseRMI;
	}
	
	@Override
	public ResponseRMI addPersonne(EntityPersonne p) {
		
		System.out.print("Client access to addPersonne()"+"\n");	

		this.listePersonnes.add(p);
		
		String bodyResp = p.name +"est ajouté avec succées"+"\n";
		
		ResponseRMI responseRMI =new ResponseRMI("404",bodyResp);
		
		System.out.print("Service successfully done"+"\n");	
		
		return responseRMI;
	}

	@Override
	public ResponseRMI deletePersonneById(int id) {


		
		return null;
	}
	




	@Override
	public ResponseRMI updatePersonne(EntityPersonne p) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
