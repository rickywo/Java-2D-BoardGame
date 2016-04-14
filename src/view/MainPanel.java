package view;

import java.awt.*;
import javax.swing.*;

import controller.GameController;
import model.gameModel.MainGame;
import resources.Consts;


public class MainPanel {
    private GridPanelRunnable gameview;


    public MainPanel(MainGame gameManager) {
        gameview = new GridPanelRunnable(gameManager);
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
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameview.start();

    }

    public static void showMessageBox() {
        Runnable updateAComponent = new Runnable() {
            public void run() { JOptionPane.showMessageDialog(null, "Team "+ GameController.getTeamOnMove() +"'s turn."); }
        };
        SwingUtilities.invokeLater(updateAComponent);

    }
}
