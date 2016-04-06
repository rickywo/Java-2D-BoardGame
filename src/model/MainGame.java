package model;
import java.util.*;

import controller.GameController;
import model.Weapon.Weapons;

public class MainGame {

	//board has 20? weapons at start
	private static final int NUM_WEAPONS = 20;
	private Weapon[] boardWeapons = new Weapon[NUM_WEAPONS];
	private int turn;
	private static MainGame game = null;
	private static EntityFactory entityFactory = null;
	
	public MainGame() {
		turn = 0;
		initialSetup();
	}
	
	public static MainGame singleton() {
		if(game == null) {
			game = new MainGame();
		}
		return game;
	}

	public void initialSetup(){
		generateBoard();
		generatePieces();
		entityFactory.getHumanTeam(); //remove later
		entityFactory.getAlienTeam(); //remove later
		generateWeapons();
		getWeapons(); //remove later
	}
	
	private void generateBoard(){
		//link to hexgame class
	}
	
	private void generatePieces(){
		entityFactory = new EntityFactory();
		entityFactory.initialisePieces();
		//check pieces generated correctly
		
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
	
	public Weapon[] getWeapons(){
		//TODO remove this later
		System.out.println("Creating Weapons...");
		for(int i=0; i<boardWeapons.length; i++){
			System.out.println(boardWeapons[i].getName());
		}
		return boardWeapons;
	}

	public void dispatchPieces(BoardCell[][] board){
		// set Chief position
		board[0][0].setEntity(entityFactory.getHumanTeam().get(0));
		board[GameController.BSIZE -1][GameController.BSIZE -1].setEntity(entityFactory.getAlienTeam().get(0));
	}
}
