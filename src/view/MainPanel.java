package view;

import java.awt.*;
import javax.swing.*;

import controller.GameController;
import resources.Consts;


public class MainPanel {
    private GridPanelRunnable gameview;


    public MainPanel(GameController gameController) {
        gameview = new GridPanelRunnable(gameController);
        createAndShowGUI();
    }

    /***************************************************************************
     * Name: createAndShowGUI(): init a Jframe to contain this game
     *****************************************************************************/

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Human vs Alien");

        Container content = frame.getContentPane();
        content.add(gameview);
        frame.setSize(Consts.SCR_WIDTH, Consts.SCR_HEIGHT);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameview.start();

    }

    public static void showVerbose(String message, long timeLimit) {
        Verbose.verbose(message, timeLimit);

    }

    public static boolean showConfirmDialog() {
        int result = JOptionPane.showConfirmDialog(null,
                "Pickup Weapon",
                "Message",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        switch (result) {
            case JOptionPane.YES_OPTION:
                return true;
            default:
                return false;
        }
    }
}
