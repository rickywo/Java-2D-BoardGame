package controller;

import model.gameModel.*;

import model.gameModel.Point;
import model.gameModel.skills.ProfessionDecorator;
import resources.Consts;
import view.Rectmech;
import view.MainPanel;


/**
 * @author Human v Alien Team
 * This class is the controller to handle UI events,
 * process the axial parameters and pass them to MainGame
 *
 * Singleton pattern is implemented here, cause only this
 * controller sitted in between view and model. Singleton
 * to ensure Main view only can create once.
 *
 * It responsible for the initailization of GameBoard.
 *
 */

public class GameController {

    private static int game_state;
    private GameBoard gameBoard; // Original in Memento Pattern
    private CareTaker careTaker; // Caretaker in Memento Pattern
    private BoardCell curMoveCell; // The entity is currently being moved
    private static int SHORT_MESSAGE = 2000;
    private static int LONG_MESSAGE = 100000;

    private static int teamOnMove = 0; // indicates which team is on moving
    private static GameController game = null;


    // Singleton pattern: to ensure this class only can be initialized
    // for once.
    public static GameController singleton() {
        if (game == null) {
            game = new GameController();
        }
        return game;
    }

    private GameController() {
        new MainPanel(this);

    }


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
     * get a number of step it can move
     */

    public int moveHandler(Point point) {

        //TODO: get a number of steps this entity can move
        curMoveCell = gameBoard.getBoardCell(point.x, point.y);
        return curMoveCell.getEntity().calculateSteps(Consts.INIT_STEPS);
    }

    public int attackHandler(Point point) {
        curMoveCell = gameBoard.getBoardCell(point.x, point.y);
        // return curMoveCell.getEntity().getAttackRange();
        return 1;
    }

    public int invokeHandler(Point point) {
        curMoveCell = gameBoard.getBoardCell(point.x, point.y);
        return curMoveCell.getEntity().getAttackRange();
    }

    /**
     * doMove(): Handle the second click on the map after moveHandler function
     * in MainPanel is called
     */

    public void doMove(Point point) {
        int xPos = curMoveCell.getEntity().getXPos();
        int yPos = curMoveCell.getEntity().getYPos();
        gameBoard.movePieceTo(xPos, yPos, point.x, point.y);
        careTaker.saveMemento(gameBoard.createMemento());
    }

    public void doAttack(Point point) {
        gameBoard.combat(curMoveCell.getEntity(), point); // curMoveCell: attacker, points: recipients
        careTaker.saveMemento(gameBoard.createMemento());
    }

    public void invoke(Point point) {
        gameBoard.invoke((ProfessionDecorator) curMoveCell.getEntity(), point); // curMoveCell: attacker, points: recipients
        careTaker.saveMemento(gameBoard.createMemento());
    }

    public void undo() {
        gameBoard.restoreMemento(careTaker.retrieveMemento());
    }


    public BoardCell getBoardCell(int x, int y) {
        return gameBoard.getBoardCell(x, y);
    }

    /**
     * switchTurn(): switches to next team
     * It called by function checkTurn in MainGame
     */

    public void switchTurn() {
        teamOnMove ++;
        teamOnMove %= Consts.NUM_TEAMS;
        MainPanel.showVerbose(Consts.TEAM_NAME[teamOnMove] +"'s turn.", SHORT_MESSAGE);
    }

    public void teamWin() {
        MainPanel.showVerbose(Consts.TEAM_NAME[teamOnMove] +" Win.", LONG_MESSAGE);
        //MainPanel.pauseGame();
    }

    public boolean foundWeapon (String weaponName) {
        return MainPanel.showConfirmDialog(weaponName);
    }

    /**
     * getTeamOnMove(): return team number of current turn
     */

    public int getTeamOnMove() {
        return teamOnMove;
    }

    public void setTeamOnMove(int i) {
        teamOnMove = i;
    }

    public void saveGame() {
        gameBoard.saveGame();
    }

    public void loadGame() {
        gameBoard.loadGame();
    }
}