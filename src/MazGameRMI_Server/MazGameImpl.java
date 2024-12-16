package MazGameRMI_Server;

import MazGameRMI_Api.MazeGameRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MazGameImpl extends UnicastRemoteObject implements MazeGameRMI {
    private static final int GRID_SIZE = 10;
    private int[][] maze;
    private Position player1Pos;
    private Position player2Pos;
    private Position treasurePos;
    private int currentPlayer = 2;

    public MazGameImpl() throws RemoteException {
        initializeMaze();
    }


    private void initializeMaze() {
        maze = new int[][]{
                {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 0, 1, 0},
                {1, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 1, 0, 0}
        };

        player1Pos = new Position(0, 0);
        player2Pos = new Position(9, 9);
        treasurePos = new Position(5, 5);

        maze[player1Pos.y][player1Pos.x] = 2;
        maze[player2Pos.y][player2Pos.x] = 3;
        maze[treasurePos.y][treasurePos.x] = 4;
    }

    @Override
    public boolean movePlayer(int playerId, int direction) throws RemoteException {
        Position currentPos = (playerId == 1) ? player1Pos : player2Pos;
        int newX = currentPos.x;
        int newY = currentPos.y;

        switch (direction) {
            case 0 -> newY--; // UP
            case 1 -> newY++; // DOWN
            case 2 -> newX--; // LEFT
            case 3 -> newX++; // RIGHT
            default -> throw new IllegalArgumentException("Invalid direction");
        }

        if (newX >= 0 && newX < GRID_SIZE && newY >= 0 && newY < GRID_SIZE && maze[newY][newX] != 1) {
            maze[currentPos.y][currentPos.x] = 0;
            currentPos.x = newX;
            currentPos.y = newY;

            if (newX == treasurePos.x && newY == treasurePos.y) {
                System.out.println("Player " + playerId + " wins!");
                initializeMaze();
                return true;
            }

            maze[newY][newX] = playerId == 1 ? 2 : 3;
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }

        return false;
    }

    @Override
    public int[][] getMazeState() throws RemoteException {
        return maze;
    }

    @Override
    public int getCurrentPlayer() throws RemoteException {
        return currentPlayer;
    }

    private static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
