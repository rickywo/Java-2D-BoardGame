package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import controller.GameController;
import model.graphicModel.ImageManager;
import resources.Consts;


public class MainPanel {
    private GridPanelRunnable gameview;
    private GameController gameController;
    private Container content;
    private JFrame frame;
    private SettingPanel settingPanel;


    public MainPanel(GameController gameController) {
        this.gameController = gameController;
        settingPanel = new SettingPanel(this);
        createAndShowGUI();
    }

    /***************************************************************************
     * Name: createAndShowGUI(): init a Jframe to contain this game
     *****************************************************************************/

    private void createAndShowGUI() {
        frame = new JFrame("Human vs Alien");
        JMenuBar menuBar = new SystemMenu(frame, this);
        frame.setJMenuBar(menuBar);
        content = frame.getContentPane();
        frame.setSize(Consts.SCR_WIDTH, Consts.SCR_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initSettingPage();
    }

    public void initSettingPage() {
        if(gameview != null) {
            gameview.stop();
            content.remove(gameview);
        }
        frame.setBounds(100, 100, 600, 300);
        content.add(settingPanel);
    }

    public void startGame(int bsize, int num_pieces, int num_weapons) {
        gameController.startGame(bsize, num_pieces, num_weapons);
        content.remove(settingPanel);
        frame.setSize(Consts.SCR_WIDTH, Consts.SCR_HEIGHT);
        gameview = new GridPanelRunnable(gameController);
        content.add(gameview);
        gameview.start();
    }

    public void pauseGame() {
        if(gameview != null) {
            gameview.stop();
        }
    }

    public void resumeGame() {
        if(gameview != null) {
            gameview.start();
        }
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
