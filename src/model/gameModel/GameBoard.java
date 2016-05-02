package model.gameModel;
import java.util.*;

import controller.GameController;
import model.gameModel.skills.Attack;
import resources.Consts;

public class GameBoard {

	public static EntityFlyweightFactory fwFactory = new EntityFlyweightFactory();
	//board has 20? weapons at start
	private static final int NUM_WEAPONS = Consts.NUM_WEAPONS;
	private final int numOfTeams = Consts.NUM_TEAMS;
	private int turn;
	private static GameBoard game = null;
	private static TeamManager teamManager = null;
	//Board variables
	private Weapon[] boardWeapons = new Weapon[NUM_WEAPONS];
	private final int BSIZE = Consts.BSIZE; //board size.
	//radius of squares to generate pieces around leader (5x5 grid) - 1
	private final static int DIST = Consts.DIST;
	private GameController controller;
	public static BoardCell[][] gameBoard;


	public GameBoard(GameController controller) {
		turn = 0;
		this.controller = controller;
		initialSetup();
	}
	
	public static GameBoard singleton(GameController controller) {
		if(game == null) {
			game = new GameBoard(controller);
		}
		return game;
	}

	public void initialSetup(){
		generateBoard();
		generatePieces();
		generateWeapons();
		dispatchPieces();
		dispatchWeapons();
        teamManager.printAllPiecesAttributes(); //remove later
		printAllWeaponInfo(); //remove later
		printBoard(); //remove later
	}
	
	private void generateBoard(){
		gameBoard = new BoardCell[BSIZE][BSIZE];
		for(int i=0; i<gameBoard[0].length; i++){
			for(int j=0; j<gameBoard[1].length; j++){
				gameBoard[i][j] = new BoardCell();
			}
		}
	}
	
	private void generatePieces(){
        teamManager = new TeamManager();
	}
	
	private void generateWeapons(){
		for(int i=0; i<boardWeapons.length; i++){
			//create weapons and place on board
			Random rand = new Random();
			int num = rand.nextInt(6) + 1;
			Weapon weapon = null;
			switch(num) {
				case 1: 
					weapon = new Weapon(Weapon.Weapons.CANNON);
					break;
				case 2: 
					weapon = new Weapon(Weapon.Weapons.GUN);
					break;	
				case 3: 
					weapon = new Weapon(Weapon.Weapons.MAGICALHANDS);
					break;
				case 4: 
					weapon = new Weapon(Weapon.Weapons.SHIELD);
					break;
				case 5: 
					weapon = new Weapon(Weapon.Weapons.COMBATKIT);
					break;
				case 6: 
					weapon = new Weapon(Weapon.Weapons.FLAG);
					break;
			}
			boardWeapons[i] = weapon;
		}
	}
	
	private void dispatchWeapons(){
		Random rand = new Random();
		int x; //xPos of weapon
		int y; //yPos of weapon
		//set weapons to board square
		for(int i=0; i<boardWeapons.length; i++){
			//check if square in empty
			do {
				x = rand.nextInt(BSIZE);
				y = rand.nextInt(BSIZE);
			} while (gameBoard[x][y].getEntity() != null
					|| gameBoard[x][y].getWeapon() != null);
			gameBoard[x][y].setWeapon(boardWeapons[i]);
			boardWeapons[i].setPos(x,y);
		}
	}

	public Weapon[] getWeapons(){
		return boardWeapons;
	}

	public void printAllWeaponInfo(){
		System.out.println("WEAPON INFO:");
		for(int i=0; i<boardWeapons.length; i++){
			boardWeapons[i].printWeaponInfo();
		}
	}


	public BoardCell[][] getBoard(){
		return gameBoard;
	}

	public BoardCell getBoardCell(int x, int y) {
		return gameBoard[x][y];
	}

	public void movePieceTo(int xo, int yo, int xd, int yd) {
		Entity t = getBoardCell(xo, yo).getEntity();
		//t.setMoved();


		t.moveTo(t, xd, yd);
		getBoardCell(xd, yd).setEntity(t);
		getBoardCell(xo, yo).clearEntity();
		System.out.println("x: " + t.getXPos() + " y:" + t.getYPos());
		if(t.isUpgradable()) checkWeapon(xd, yd);
        checkTurn();
	}

	public void combat(Entity attacker, Point recipient) {
		// TODO: to call attack function of attack and apply attacking to those recipients
		Entity t = getBoardCell(recipient.x, recipient.y).getEntity();
		System.out.println("Before invoke");
		if(t == null) return;
		attacker.attack(t);
		//attacker.invokeSkill(new Attack(attacker.getStrength()), t);
		System.out.println("hp:" + t.getCurrentHP());
		if(t.getCurrentHP() <= 0) {
			destroyEntity(recipient.x, recipient.y);
		}
		System.out.println("After");
	}

	private void destroyEntity(int x, int y) {
		int i = controller.getTeamOnMove();
		BoardCell cell = getBoardCell(x, y);
		teamManager.getTeam(i).remove(cell.getEntity());
		cell.clearEntity();

	}

	private void dispatchPieces(){
		dispatchHumanTeam();
		dispatchAlienTeam();
	}

	private void dispatchHumanTeam(){
		//SET HUMAN TEAM POSITIONS
		//Set Commander position
		Random rand = new Random();
		int x = rand.nextInt(BSIZE-DIST-1) + (DIST/2);
        int y = rand.nextInt(BSIZE-DIST-1) + (DIST/2);
		Team team = teamManager.getTeam(0);
		getBoardCell(x, y).setEntity(team.get(0));
		team.get(0).setPos(x,y);

		//Set all other human pieces
		for(int i=1; i<team.size(); i++){
			int a, b;
			//find an available square around leader (within 5x5 grid)
			do {
				a = rand.nextInt(DIST) + x - (DIST/2);
				b = rand.nextInt(DIST) + y - (DIST/2);
			} while( a >= BSIZE || b >= BSIZE || a < 0 || b < 0
					|| getBoardCell(a, b).getEntity() != null);
			//set piece to that square
			getBoardCell(a, b).setEntity(team.get(i));
			team.get(i).setPos(a,b);
		}
	}

	private void dispatchAlienTeam(){
		//SET ALIEN TEAM POSITIONS
		//Set Chief position and check there is clear space of 5x5 grid
		//around chief
		Random rand = new Random();
		int x;	//xPos of chief
		int y;	//yPos of chief
		int clearSquares = 0; //number of empty squares around chief
		//check for clear space around Chief
		do {
			x = rand.nextInt(BSIZE-DIST-1) + (DIST/2);
			y = rand.nextInt(BSIZE-DIST-1) + (DIST/2);
			for(int k=x-(DIST/2); k<x+(DIST/2); k++){
				for(int l=x-(DIST/2); l<x+(DIST/2); l++){
					//this square is occupied by Chief - count as empty square
					if(k==x && l==y){
						clearSquares++;
					} //this square is outside the grid IndexOutOfBounds
					else if(k<0 || l<0 || k>=BSIZE || l>=BSIZE){
						continue;
					} //valid square for checking if has entity already
					else {
						//square is not occupied
						if(getBoardCell(k, l).getEntity() == null){
							clearSquares++;
						}
					}
				}
			}
		} while(clearSquares<((DIST+1)*(DIST+1)));	//5x5 grid is empty

		Team team = teamManager.getTeam(1);
		//set Chief position
		getBoardCell(x, y).setEntity(team.get(0));
		team.get(0).setPos(x,y);
		//Set all other human pieces
		for(int i=1; i<team.size(); i++){
			int a, b;
			//find an available square around leader (within 5x5 grid)
			do {
				a = rand.nextInt(DIST) + x - (DIST/2);
				b = rand.nextInt(DIST) + y - (DIST/2);
			} while(a >= BSIZE || b >= BSIZE || a < 0 || b < 0
					|| getBoardCell(a, b).getEntity() != null);
			//set piece to that square
			getBoardCell(a, b).setEntity(team.get(i));
			team.get(i).setPos(a,b);
		}
	}

	public  void updateBoard() {

	}

	//For debugging only
	public void printBoard(){
		//copy board
		String[][] consoleBoard = new String[BSIZE][BSIZE];
		for(int i=0; i<BSIZE; i++){
			for(int j=0; j<BSIZE; j++){
				if(getBoardCell(i, j).getEntity() != null){
					consoleBoard[i][j] = " E ";
				} else if(getBoardCell(i, j).getWeapon() != null){
					consoleBoard[i][j] = " W ";
				} else { consoleBoard[i][j] = " . "; }
			}
		}
		for(int i=0; i<BSIZE; i++){
			System.out.println();
			for(int j=0; j<BSIZE; j++){
				System.out.print(consoleBoard[i][j]);
			}
		}
	}

    /***************************************************************************
     * checkTurn(): go over all entities in a team to see if any of them has not
     * been moved
     *
     * It called by function movePieceTo(). It calls switchTurn when all entities
     * are finished its movement
     *****************************************************************************/

    private void checkTurn() {
        final int team = controller.getTeamOnMove();
        if(teamManager.isTeamsTurnFinished(team)) {
			System.out.println("Team on move: " + team);
            teamManager.resetTeamMoved(team);
            controller.switchTurn();
        }
    }

	private void checkWeapon(int x, int y) {
		Weapon weapon = getBoardCell(x, y).getWeapon();

		if(weapon!=null) {
			Entity target = gameBoard[x][y].getEntity();
			if(controller.foundWeapon(weapon.getName())) {
				getBoardCell(x, y).setEntity(ProfessionManager.changeProfession(target, weapon.getType()));
				getBoardCell(x, y).clearWeapon();
			}
		}
	}
}
