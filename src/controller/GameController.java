package controller;

import model.BoardCell;

import model.Entity;
import model.MainGame;
import resources.Consts;
import view.Rectmech;
import view.MainPanel;

import java.awt.*;

/**
 *
 * @author Human v Alien Team
 * This class is the controller to handle UI events,
 * process the axial parameters and pass them to MainGame
 *
 *
 */

public class GameController
{
	private MainPanel main;
	private MainGame gameManager;
	private boolean mapInit = false;
	private boolean moveLock = false;
	//constants and global variables
	private BoardCell curMoveCell; // The entity is currently being moved
	private Point curMovePoint;


	private static GameController game = null;
	
	public static GameController singleton() {
		if(game == null) {
			game = new GameController();
		}
		return game;
	}
	private GameController() {
		//

		initGame();
		main = new MainPanel(gameManager.getBoard(), this);

	}



	void initGame(){


		Rectmech.setLength(Consts.RECTSIZE);
		Rectmech.setBorders(Consts.BORDERS);
		mapInit = true;
		//set up board here
		gameManager = MainGame.singleton();
		//gameManager.dispatchPieces(gameBoard); - MOVED TO MainGame class
	}
	
	public boolean isMapInit() {
		return mapInit;
	}

	/**
	 * moveHandler(): Handle the first click on move menu
	 * Parameters: point: the coordinate on the map
	 */

	public int moveHandler(Point point) {

		moveLock = true;
		//TODO: get how many steps can move of this piece
		curMovePoint = point;
		curMoveCell = gameManager.getBoardCell(point.x, point.y);
		return curMoveCell.getEntity().calculateSteps(Consts.INIT_STEPS);
	}

	/**
	* doMove(): Handle the second click on the map after moveHandler is called
	*/
	public void doMove(Point point) {
		System.out.println("Move to " + point.x + ", " + point.y);
		gameManager.movePieceTo(curMovePoint.x, curMovePoint.y, point.x, point.y);
		moveLock = false;
	}

	public boolean isMoveLocked() {
		return moveLock;
	}
}