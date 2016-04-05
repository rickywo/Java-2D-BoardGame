package controller;
import java.awt.*;

import javax.swing.*;

import model.ImageManager;

import java.awt.event.*; 
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
	
	public final static int RECTSIZE = 40;	//hex size in pixels
	public final static int BORDERS = 16;  
	public final static int SCRSIZE = RECTSIZE * (MainGame.BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).

	public int[][] board = new int[MainGame.BSIZE][MainGame.BSIZE];
	public BufferedImage [][] map = new BufferedImage[MainGame.BSIZE][MainGame.BSIZE];

	void initGame(){

		Rectmech.setLength(RECTSIZE); //Either setHeight or setSize must be run to initialize the hex
		Rectmech.setBorders(BORDERS);

		for (int i=0;i<MainGame.BSIZE;i++) {
			for (int j=0;j<MainGame.BSIZE;j++) {
				map[i][j] = ImageManager.getRandomTiles();
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