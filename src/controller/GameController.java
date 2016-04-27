package controller;

import model.gameModel.*;

import model.gameModel.Point;
import resources.Consts;
import view.Rectmech;
import view.MainPanel;


/**
 * @author Human v Alien Team
 *         This class is the controller to handle UI events,
 *         process the axial parameters and pass them to MainGame
 */

public class GameController {

    private static int game_state;
    private MainGame modelManager;
    private BoardCell curMoveCell; // The entity is currently being moved

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
        initGame();
        new MainPanel(modelManager);

    }


    void initGame() {
        Rectmech.setLength(Consts.RECTSIZE);
        Rectmech.setBorders(Consts.BORDERS);
        modelManager = MainGame.singleton();
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
        curMoveCell = modelManager.getBoardCell(point.x, point.y);
        return curMoveCell.getEntity().calculateSteps(Consts.INIT_STEPS);
    }

    public int attackHandler(Point point) {
        curMoveCell = modelManager.getBoardCell(point.x, point.y);
        return curMoveCell.getEntity().getAttackRange();
    }

    /**
     * doMove(): Handle the second click on the map after moveHandler function
     * in MainPanel is called
     */

    public void doMove(Point point) {
        int xPos = curMoveCell.getEntity().getXPos();
        int yPos = curMoveCell.getEntity().getYPos();
        modelManager.movePieceTo(xPos, yPos, point.x, point.y);
    }

    public void doAttack(Point point) {
        modelManager.combat(curMoveCell.getEntity(), point); // curMoveCell: attacker, points: recipients
    }

    /**
     * switchTurn(): switches to next team
     * It called by function checkTurn in MainGame
     */

    public static void switchTurn() {
        teamOnMove ++;
        teamOnMove %= Consts.NUM_TEAMS;
        MainPanel.showMessageBox(Consts.TEAM_NAME[teamOnMove] +"'s turn.", 2000);
    }

    /**
     * getTeamOnMove(): return team number of current turn
     */

    public static int getTeamOnMove() {
        return teamOnMove;
    }


    public static void setGameState(int state) {
        game_state = state;
    }

    public static int getGameState() {
        return game_state;
    }
}