package MazGameRMI_Api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MazeGameRMI extends Remote {
    boolean movePlayer(int playerId, int direction) throws RemoteException;
    int[][] getMazeState() throws RemoteException;
    int getCurrentPlayer() throws RemoteException;
}

