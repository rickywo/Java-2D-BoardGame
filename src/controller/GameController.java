package controller;

import model.ImageManager;
import java.awt.image.BufferedImage;

import view.Rectmech;
import view.MainPanel;

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
	private Boolean mapInit = false;
	private static GameController game = null;
	
	public static GameController singleton() {
		if(game == null) {
			game = new GameController();
		}
		return game;
	}
	private GameController() {
		main = new MainPanel(board, this);
		initGame();

	}

	//constants and global variables
	
	public final static int EMPTY = 0;
	public final static int BSIZE = 16; //board size.
	public final static int RECTSIZE = 32;	//hex size in pixels
	public final static int BORDERS = 16;  
	public final static int SCRSIZE = RECTSIZE * (BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).

	public int[][] board = new int[BSIZE][BSIZE];
	public BufferedImage [][] map = new BufferedImage[BSIZE][BSIZE];

	void initGame(){


		Rectmech.setLength(RECTSIZE);
		Rectmech.setBorders(BORDERS);

		for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				map[i][j] = ImageManager.getRandomTiles();
				board[i][j] = EMPTY;
			}
		}
		mapInit = true;
		//set up board here
	}
	
	public boolean isMapInit() {
		return mapInit;
	}
	
	public BufferedImage[][] getMap() {
		return map;
	}
}