/*
 * Copyright (C) 2016 Ricky Wu.
 */
package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import controller.GameController;
import model.graphicModel.ImageManager;
import resources.Consts;


/**
 * The Class MainPanel. Main class responsible for handling java 2d 
 * and awt component
 */
public class MainPanel {
    
    /** The gameview. */
    private static GridPanelRunnable gameview;
    
    /** The game controller. */
    private GameController gameController;
    
    /** The content. */
    private Container content;
    
    /** The frame. */
    private JFrame frame;
    
    /** The setting panel. */
    private SettingPanel settingPanel;


    /**
     * Instantiates a new main panel.
     *
     * @param gameController the game controller
     */
    public MainPanel(GameController gameController) {
        this.gameController = gameController;
        settingPanel = new SettingPanel(this);
        createAndShowGUI();
    }

    /**
     * 
     * Name: createAndShowGUI(): init a Jframe to contain this game
     * 
     */

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

    /**
     * Initialize the setting page.
     */
    public void initSettingPage() {
        if(gameview != null) {
            gameview.stop();
            content.remove(gameview);
        }
        frame.setBounds(100, 100, 600, 300);
        content.add(settingPanel);
    }

    /**
     * Start game.
     *
     * @param bsize the bsize
     * @param num_pieces the num_pieces
     * @param num_weapons the num_weapons
     */
    public void startGame(int bsize, int num_pieces, int num_weapons) {
        gameController.startGame(bsize, num_pieces, num_weapons);
        content.remove(settingPanel);
        frame.setSize(Consts.SCR_WIDTH, Consts.SCR_HEIGHT);
        gameview = new GridPanelRunnable(gameController);
        content.add(gameview);
        gameview.start();
    }

    /**
     * Pause game.
     */
    public static void pauseGame() {
        if(gameview != null) {
            gameview.stop();
        }
    }

    /**
     * Resume game.
     */
    public static void resumeGame() {
        if(gameview != null) {
            gameview.start();
        }
    }

    /**
     * Undo.
     */
    public void undo() {
        gameController.undo();
    }

    /**
     * Save.
     */
    public void save() {
        gameController.saveGame();
    }

    /**
     * Load.
     */
    public void load() {
        gameController.loadGame();
    }

    public static void setAttackEffect(String type) {
        gameview.prepareAttackEffect(type);
    }

    /**
     * Show verbose.
     *
     * @param message the message
     * @param timeLimit the time limit
     */
    public static void showVerbose(String message, long timeLimit) {
        Verbose.verbose(message, timeLimit);

    }

    /**
     * Show confirm dialog.
     *
     * @param weaponName the weapon name
     * @return true, if successful
     */
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

    /**
     * Show attack icon.
     *
     * @param g2 the g2
     * @param x the x
     * @param y the y
     * @param i the i
     */
    public static void showAttackEffect(final Graphics2D g2, int x, int y, int i, String type) {
        BufferedImage image = null;
        switch(i) {
            case 0:
                image = ImageManager.getAttackEffect(type+"3");
                break;
            case 1:
                image = ImageManager.getAttackEffect(type+"2");
                break;
            case 2:
                image = ImageManager.getAttackEffect(type+"1");
                break;
            default:
                break;
        }
        Rectmech.draw(x,y, image, g2);

    }
}
