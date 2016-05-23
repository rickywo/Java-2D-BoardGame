/*
 * Copyright (C) 2016 Ricky Wu.
 */
package controller;

import model.gameModel.*;

import model.gameModel.Point;
import model.gameModel.skills.ProfessionDecorator;
import resources.Consts;
import view.Rectmech;
import view.MainPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class GameController.
 *
 * @author Human v Alien Team
 * This class is the controller to handle UI events,
 * process the axial parameters and pass them to MainGame
 * 
 * Singleton pattern is implemented here, cause only this
 * controller sitted in between view and model. Singleton
 * to ensure Main view only can create once.
 * 
 * It responsible for the initailization of GameBoard.
 */

public class GameController {

    /** The game board reference. */
    private GameBoard gameBoard; // Original in Memento Pattern
    
    /** The care taker reference. */
    private CareTaker careTaker; // Caretaker in Memento Pattern
    
    /** The current being moved cell. */
    private BoardCell curMoveCell; // The entity is currently being moved
    
    /** The short message displaying time constant. */
    private static final int SHORT_MESSAGE = 2000;
    
    /** The long message displaying time constant. */
    private static final int LONG_MESSAGE = 100000;


    /** The GameController reference for singleton pattern. */
    private static GameController game = null;


    /**
     * Singleton.
     * Singleton pattern: to ensure this class only can be initialized once
     * @return the game controller
     */
    
    public static GameController singleton() {
        if (game == null) {
            game = new GameController();
        }
        return game;
    }

    /**
     * Instantiates a new game controller.
     */
    private GameController() {
        new MainPanel(this);

    }


    /**
     * Start game.
     *
     * @param bsize the board
     * @param num_pieces the number of pieces in a team
     * @param num_weapons the number of weapons on the map initially
     */
    public void startGame(int bsize, int num_pieces, int num_weapons) {
        Consts.setBSIZE(bsize);
        Consts.setNumPiecesPerTeam(num_pieces);
        Consts.setNumWeapons(num_weapons);
        Rectmech.setLength(Consts.getRectsize());
        gameBoard = new GameBoard(this);
        careTaker = new CareTaker();
    }


    /**
     * moveHandler(): Handle the first click on move menu
     * Parameters: point: the coordinate on the map
     * It gets a point from movePiece function in GridPanel
     * then calls calculateSteps function of a entity to
     * get a number of step it can move.
     *
     * @param point the point
     * @return the int
     */

    public int moveHandler(Point point) {

        //TODO: get a number of steps this entity can move
        curMoveCell = gameBoard.getBoardCell(point.x, point.y);
        return curMoveCell.getEntity().calculateSteps(Consts.INIT_STEPS);
    }

    /**
     * Attack handler.
     * To handle the event when attack item is clicked on action menu
     *
     * @param point the point
     * @return the int
     */
    public int attackHandler(Point point) {
        curMoveCell = gameBoard.getBoardCell(point.x, point.y);
        return 1;
    }

    /**
     * Invoke handler.
     * To handle the event when invoke item is clicked on action menu
     * @param point the point
     * @return the int
     */
    public int invokeHandler(Point point) {
        curMoveCell = gameBoard.getBoardCell(point.x, point.y);
        return curMoveCell.getEntity().getAttackRange();
    }

    /**
     * doMove(): Handle the second click on the map after moveHandler function
     * in MainPanel is called.
     *
     * @param point the cooridinate of a cell being clicked
     */

    public void doMove(Point point) {
        int xPos = curMoveCell.getEntity().getXPos();
        int yPos = curMoveCell.getEntity().getYPos();
        gameBoard.movePieceTo(xPos, yPos, point.x, point.y);
        careTaker.saveMemento(gameBoard.createMemento());
    }

    /**
     * doAttack(): Handle the second click on the map after attackHandler function
     *
     * @param point the cooridinate of a cell being clicked
     */
    public void doAttack(Point point) {
        gameBoard.combat(curMoveCell.getEntity(), point); // curMoveCell: attacker, points: recipients
        careTaker.saveMemento(gameBoard.createMemento());
    }

    /**
     * Invoke: Handle the second click on the map after invokeHandler function
     *
     * @param point the cooridinate of a cell being clicked
     */
    public void invoke(Point point) {
        gameBoard.invoke((ProfessionDecorator) curMoveCell.getEntity(), point); // curMoveCell: attacker, points: recipients


        careTaker.saveMemento(gameBoard.createMemento());
    }

    /**
     * Undo.
     * Undo last movement.
     */
    public void undo() {
        gameBoard.restoreMemento(careTaker.retrieveMemento());
    }


    /**
     * Gets the board cell by a specific cooridinates.
     *
     * @param x the x
     * @param y the y
     * @return the board cell
     */
    public BoardCell getBoardCell(int x, int y) {
        return gameBoard.getBoardCell(x, y);
    }

    /**
     * switchTurn(): switches to next team
     * It called by function checkTurn in MainGame.
     */

    public void switchTurn() {
        MainPanel.showVerbose(Consts.TEAM_NAME[gameBoard.getTeamOnMove()] +"'s turn.", SHORT_MESSAGE);
    }

    /**
     * Show saving verbose.
     */
    public void showSavingVerbose() {
        MainPanel.showVerbose("Saving game", SHORT_MESSAGE);
    }

    /**
     * Team win.
     * To show a Winning message on the screen
     */
    public void teamWin() {
        MainPanel.showVerbose(Consts.TEAM_NAME[gameBoard.getTeamOnMove()] +" Win.", LONG_MESSAGE);
    }

    /**
     * Found weapon.
     * To show a comfirm Dialog to accept a response from user
     *
     * @param weaponName the weapon name
     * @return true, if successful
     */
    public boolean foundWeapon (String weaponName) {
        return MainPanel.showConfirmDialog(weaponName);
    }

    /**
     * getTeamOnMove(): return team number of current turn.
     *
     * @return the team on move
     */

    public int getTeamOnMove() {
        return gameBoard.getTeamOnMove();
    }

    /**
     * Save game.
     */
    public void saveGame() {
        gameBoard.saveGame();
    }

    /**
     * Load game.
     */
    public void loadGame() {
        gameBoard.loadGame();
    }
}