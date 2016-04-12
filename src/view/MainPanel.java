package view;

import java.awt.*;
import javax.swing.*;

import controller.GameController;
import model.*;
import resources.Consts;


public class MainPanel {
    private GridPanel panel;
    private JFrame frame;
    private Container content;


    public MainPanel(MainGame gameManager) {
        panel = new GridPanel(gameManager);
        createAndShowGUI();
    }

    /***************************************************************************
     * Name: createAndShowGUI(): init a Jframe to contain this game
     *****************************************************************************/

    private void createAndShowGUI() {
        frame = new JFrame("Human vs Alien");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = frame.getContentPane();
        content.add(panel);
        frame.setSize(Consts.SCR_WIDTH, Consts.SCR_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void showMessageBox() {
        Runnable updateAComponent = new Runnable() {
            public void run() { JOptionPane.showMessageDialog(null, "Team "+ GameController.getTeamOnMove() +"'s turn."); }
        };
        SwingUtilities.invokeLater(updateAComponent);

    }
}
