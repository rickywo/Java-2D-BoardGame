package controller;

import java.util.*;

import model.*;
import model.Weapon.Weapons;

/**
 * 
 * @author Human v Alien Team
 * MainGame Class: controller for game system,
 * deals with game board, pieces and weapons so on.
 * Handles game turns, pieces' movement, combats
 *   
 *
 */
public class MainGame {

	//board has 20? weapons at start
	public final static int BSIZE = 16; //board size.
	public final static Entity EMPTY = null;
	private static final int NUM_WEAPONS = 20;
	private Entity[][] board = new Entity[BSIZE][BSIZE];
	private Weapon[] boardWeapons = new Weapon[NUM_WEAPONS];
	private int turn;
	
	public MainGame() {
		turn = 0;
		initialSetup();
	}

	public void initialSetup(){
		generateBoard();
		generatePieces();
		generateWeapons();
	}
	
	private void generateBoard(){
		for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				board[i][j] = EMPTY;
			}
		}
		//link to hexgame class
	}
	
	private void generatePieces(){
		EntityFactory entityFactory = new EntityFactory();
		entityFactory.initialisePieces();
	}
	
	private void generateWeapons(){
		for(int i=0; i<boardWeapons.length; i++){
			//create weapons and place on board
			Random rand = new Random();
			int num = rand.nextInt(6) + 1;
			Weapon weapon = null;
			switch(num) {
				case 1: 
					weapon = new Weapon(Weapons.CANNON);
					break;
				case 2: 
					weapon = new Weapon(Weapons.GUN);
					break;	
				case 3: 
					weapon = new Weapon(Weapons.MAGICALHANDS);
					break;
				case 4: 
					weapon = new Weapon(Weapons.SHIELD);
					break;
				case 5: 
					weapon = new Weapon(Weapons.COMBATKIT);
					break;
				case 6: 
					weapon = new Weapon(Weapons.FLAG);
					break;
			}
			boardWeapons[i] = weapon;
		}
	}
}
