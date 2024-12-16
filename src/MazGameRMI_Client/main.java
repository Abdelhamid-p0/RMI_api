package MazGameRMI_Client;

import javax.swing.*;

public class main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MazeGameUI(args.length > 0 ? Integer.parseInt(args[0]) : 2).setVisible(true));
    }
}
