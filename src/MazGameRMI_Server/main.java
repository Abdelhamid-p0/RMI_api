package MazGameRMI_Server;

import MazGameRMI_Api.MazeGameRMI;


public class main {

    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            MazeGameRMI server = new MazGameImpl();
            java.rmi.Naming.rebind("MazeGame", server);
            System.out.println("MazeGame Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
