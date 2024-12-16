package MazGameRMI_Client;
import MazGameRMI_Api.MazeGameRMI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;

public class MazeGameUI extends JFrame {
    private MazeGameRMI server;
    private int playerId;
    private int[][] maze;

    public MazeGameUI(int playerId) {
        this.playerId = playerId;
        setTitle("Maze Game - Player " + playerId);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            server = (MazeGameRMI) Naming.lookup("rmi://100.89.17.155/MazeGame");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        initializeGUI();
        refreshMaze();
    }



    private void initializeGUI() {
        JPanel mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (maze != null) {
                    int cellSize = 50;
                    for (int row = 0; row < maze.length; row++) {
                        for (int col = 0; col < maze[row].length; col++) {
                            int x = col * cellSize;
                            int y = row * cellSize;

                            switch (maze[row][col]) {
                                case 1 -> g.setColor(Color.DARK_GRAY);
                                case 2 -> g.setColor(Color.BLUE);
                                case 3 -> g.setColor(Color.RED);
                                case 4 -> g.setColor(Color.YELLOW);
                                default -> g.setColor(Color.WHITE);
                            }

                            g.fillRect(x, y, cellSize, cellSize);
                            g.setColor(Color.BLACK);
                            g.drawRect(x, y, cellSize, cellSize);
                        }
                    }
                }
            }
        };

        mazePanel.setPreferredSize(new Dimension(500, 500));
        add(mazePanel, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int direction = switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> 0;
                    case KeyEvent.VK_DOWN -> 1;
                    case KeyEvent.VK_LEFT -> 2;
                    case KeyEvent.VK_RIGHT -> 3;
                    default -> -1;
                };

                if (direction != -1) {
                    try {
                        if (server.movePlayer(playerId, direction)) {
                            JOptionPane.showMessageDialog(MazeGameUI.this,
                                    "Player " + playerId + " wins!",
                                    "Game Over",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        refreshMaze();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
    }

    private void refreshMaze() {
        try {
            maze = server.getMazeState();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
