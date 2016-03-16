package controller;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import view.Hexgrid;
import view.MainPanel;

/**********************************
  This is the main class of a Java program to play a game based on hexagonal tiles.
  The mechanism of handling hexes is in the file hexmech.java.

  Written by: M.H.
  Date: December 2012

 ***********************************/

public class Hexgame
{
	private MainPanel main;
	private static Hexgame game = null;
	
	public static Hexgame singleton() {
		if(game == null) {
			game = new Hexgame();
		}
		return game;
	}
	private Hexgame() {
		main = new MainPanel(board);
		initGame();

	}

	//constants and global variables
	
	public final static int EMPTY = 0;
	public final static int BSIZE = 12; //board size.
	public final static int HEXSIZE = 60;	//hex size in pixels
	public final static int BORDERS = 15;  
	public final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).

	public int[][] board = new int[BSIZE][BSIZE];

	void initGame(){

		Hexgrid.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.

		Hexgrid.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
		Hexgrid.setBorders(BORDERS);

		for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				board[i][j]=EMPTY;
			}
		}

		//set up board here
/*		board[3][3] = (int)'A';
		board[4][3] = (int)'Q';
		board[4][4] = -(int)'B';*/
	}
}