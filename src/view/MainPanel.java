package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import javax.swing.*;

import controller.GameController;
import model.graphicModel.Art;
import model.graphicModel.ImageManager;
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

    public static boolean showConfirmDialog(String weaponName) {
        int result = JOptionPane.showConfirmDialog(null,
                "Pickup " + weaponName,
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

    public static void showAttackIcon(final Graphics2D g2, int x, int y, int i) {
        System.out.println("Mainpanel Attack cell x: " + x + " y:" + y);
        BufferedImage image = null;
        switch(i) {
            case 0:
                image = ImageManager.getAttackEffect("sword3");
                break;
            case 1:
                image = ImageManager.getAttackEffect("sword2");
                break;
            case 3:
                image = ImageManager.getAttackEffect("sword1");
                break;
            default:
                break;
        }
        Rectmech.draw(x,y, image, g2);

    }
}
