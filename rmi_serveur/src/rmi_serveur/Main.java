package rmi_serveur;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi_api.ServicePersonne;

public class Main {

    public static void main(String[] args) throws RemoteException {
        System.out.print("Serveur Running " + "\n");

        // Définir l'adresse IP du serveur
        System.setProperty("java.rmi.server.hostname", "192.168.11.106"); // Remplacez par l'IP de votre serveur

        Registry registry = LocateRegistry.createRegistry(5002);

        ServisePersonneImpl serviceImpl = new ServisePersonneImpl();

        ServicePersonne service = (ServicePersonne) UnicastRemoteObject.exportObject(serviceImpl, 0);

        registry.rebind("ServicePersonne", service);

        System.out.println("Service 'ServicePersonne' enregistré et prêt. \n");
    }
}
