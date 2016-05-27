package model.gameModel;
import java.util.*;

import controller.GameController;
import model.gameModel.skills.ProfessionDecorator;
import resources.Consts;
import java.io.*;

/**
 * State class
 * Responsibility:
 *
 * 1. To save current state of the game
 *
 */

class State {
	int x,y;
	int turn;
	int teamOnMove;
	Entity invoker;

	State(int x, int y, int turn, int teamOnMove, Entity invoker) {
		// The coordinator been clicked
		this.x = x;
		this.y = y;

		// Current turn of the game
		this.turn = turn;
		this.teamOnMove = teamOnMove;

		// The invoker entity of a command in this trun
		this.invoker = invoker;
	}

}

/**
 * Created by Human v Alien Team on 2016/5/16.
 * Memento behavioral design pattern:
 * Originator class
 * Responsibility:
 *
 * 1. To provides a method to create a memento(snapshot)
 * 	  object for caretaker object to save the current state
 * 	  of this game.
 *
 * 2. protected class member "Memento": to ensure information
 *    is encapsulated in memento object to avoid access from
 *    other classes.
 */

public class GameBoard implements InvokeObserverInterface {

	/** The FlyweightFactory factory instance. */
	public static EntityFlyweightFactory fwFactory = new EntityFlyweightFactory();
	
	/** The Constant TEAM_ONE_ARRAYLIST_OBJECT_INDEX. */
	private static final int TEAM_ONE_ARRAYLIST_OBJECT_INDEX = 0;
	
	/** The Constant TEAM_TWO_ARRAYLIST_OBJECT_INDEX. */
	private static final int TEAM_TWO_ARRAYLIST_OBJECT_INDEX = 1;
	
	/** The Constant BOARD_SIZE_OBJECT_INDEX. */
	private static final int BOARD_SIZE_OBJECT_INDEX = 2;
	
	/** The Constant TURN_COUNT_OBJECT_INDEX. */
	private static final int TURN_COUNT_OBJECT_INDEX = 3;
	
	/** The Constant TEAM_ON_MOVE_OBJECT_INDEX. */
	private static final int TEAM_ON_MOVE_OBJECT_INDEX = 4;
	
	/** The Constant BOARD_CELL_2DARRAY_OBJECT_INDEX. */
	private static final int BOARD_CELL_2DARRAY_OBJECT_INDEX = 5;
	
	/** The Constant WEAPON_ARRAY_OBJECT_INDEX. */
	private static final int WEAPON_ARRAY_OBJECT_INDEX = 6;
	
	/** The num of weapons. */
	//board has 20? weapons at start
	private final int NUM_WEAPONS = Consts.NUM_WEAPONS;
	
	/** The board size. */
	private final int BSIZE = Consts.getBSIZE(); //board size.

	/** The turn count. */
	private int turn;
	
	/** The current state. */
	private State state;
	
	/** The team number on move. */
	private int teamOnMove; // indicates which team is on moving
	
	/** The team manager. */
	private TeamManager teamManager = null;
	
	/** The board contains weapons. */
	//Board variables
	private Weapon[] boardWeapons = new Weapon[NUM_WEAPONS];

	/** The Constant DIST. */
	//radius of squares to generate pieces around leader (5x5 grid) - 1
	private final static int DIST = Consts.DIST;
	
	/** The controller reference. */
	private GameController controller;

	/** The ObservationSubject instance */
	private ObservationSubject observationSubject;
	
	/** The game board. */
	public static BoardCell[][] gameBoard;


	/**
	 * Instantiates a new game board.
	 *
	 * @param controller the game controller reference
	 */
	public GameBoard(GameController controller) {
		turn = 0;
		teamOnMove = 0;
		state = new State(0 ,0 ,turn, 0, null);
		this.controller = controller;
		observationSubject = new ObservationSubject();
		observationSubject.addObserver(this);
		initialSetup();
	}

	/**
	 * Initial setup.
	 */
	public void initialSetup(){
		generateBoard();
		generatePieces();
		generateWeapons();
		dispatchPieces();
		dispatchWeapons();
	}
	
	/**
	 * Generate board.
	 */
	private void generateBoard(){
		gameBoard = new BoardCell[BSIZE][BSIZE];
		for(int i=0; i<gameBoard[0].length; i++){
			for(int j=0; j<gameBoard[1].length; j++){
				gameBoard[i][j] = new BoardCell();
			}
		}
	}
	
	/**
	 * Generate pieces.
	 */
	private void generatePieces(){
        teamManager = new TeamManager();
	}
	
	/**
	 * Generate weapons.
	 */
	private void generateWeapons(){
		for(int i=0; i<boardWeapons.length; i++){
			//create weapons and place on board
			Random rand = new Random();
			int num = rand.nextInt(6);
			Weapon weapon = null;
			weapon = new Weapon(num);
			boardWeapons[i] = weapon;
		}
	}
	
	/**
	 * Dispatch weapons.
	 */
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

	/**
	 * Prints the all weapon info.
	 */
	public void printAllWeaponInfo(){
		System.out.println("WEAPON INFO:");
		for(int i=0; i<boardWeapons.length; i++){
			boardWeapons[i].printWeaponInfo();
		}
	}

	/**
	 * Gets the board cell.
	 *
	 * @param x the x coordinate on game board
	 * @param y the y coordinate on game board
	 * @return the board cell
	 */
	public BoardCell getBoardCell(int x, int y) {
		return gameBoard[x][y];
	}

	/**
	 * Move piece to.
	 *
	 * @param xo from x 
	 * @param yo from y
	 * @param xd destination x
	 * @param yd destination y
	 */
	public void movePieceTo(int xo, int yo, int xd, int yd) {
		Entity t = getBoardCell(xo, yo).getEntity();
		t.moveTo(t, xd, yd);
		saveState(xo, yo, t);
		updateBoard();
		if(t.isUpgradable()) checkWeapon(xd, yd);
        checkTurn();

	}

	/**
	 * Combat.
	 *
	 * @param attacker the attacker who making attack
	 * @param recipient the recipient who being attacked
	 */
	public void combat(Entity attacker, Point recipient) {
		// TODO: to call attack function of attacker and apply attacking to those recipients
		Entity t = getBoardCell(recipient.x, recipient.y).getEntity();
		if(t == null) return;
		attacker.attack(t, observationSubject);
		saveState(-1, -1, attacker);
		if(t.getCurrentHP() <= 0) {
			destroyEntity(recipient.x, recipient.y);
		}
		checkTurn();
	}

	/**
	 * Invoke.
	 *
	 * @param attacker the attacker who making attack
	 * @param recipient the recipient who being attacked
	 */
	public void invoke(ProfessionDecorator attacker, Point recipient) {
		// TODO: to call invoke function of attacker and apply skill attack to those recipients
		Entity t = getBoardCell(recipient.x, recipient.y).getEntity();
		if(t == null) return;
		attacker.invoke(t, observationSubject);
		saveState(-1, -1, attacker);
		if(t.getCurrentHP() <= 0) {
			destroyEntity(recipient.x, recipient.y);
		}
		checkTurn();
	}

	/**
	 * Destroy an entity.
	 * Move its cooridinates to -1, -1
	 *
	 * @param x the x
	 * @param y the y
	 */
	private void destroyEntity(int x, int y) {
		BoardCell cell = getBoardCell(x, y);
		Entity e = cell.getEntity();
		e.setPos(-1, -1);
		checkWin();
		updateBoard();
	}

	/**
	 * Dispatch pieces.
	 * Places pieces on the game board
	 */
	private void dispatchPieces(){
		dispatchHumanTeam();
		dispatchAlienTeam();
		updateBoard();
	}

	/**
	 * Dispatch human team.
	 */
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

	/**
	 * Dispatch alien team.
	 */
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

	/**
	 * Update pieces on the game board.
	 */
	public  void updateBoard() {
		for(int i=0; i<BSIZE; i++){
			for(int j=0; j<BSIZE; j++){
				Entity e = teamManager.getEntityByXY(i, j);
				gameBoard[i][j].setEntity(e);
			}
		}
	}

	/**
	 * Prints the board.
	 */
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

    /**
     * checkTurn(): go over all entities in a team to see if any of them has not
     * been moved
     *
     * It called by function movePieceTo(). It calls switchTurn when all entities
     * are finished its movement
     */

    private void checkTurn() {
        final int team = getTeamOnMove();
        if(teamManager.isTeamsTurnFinished(team)) {
            teamManager.resetTeamMoved(team);
            nextTurn();
        }
    }

	/**
	 * Check if current moving team wins.
	 */
    
	private void checkWin() {
		final int team = 1 - getTeamOnMove();
		if(teamManager.isTeamDefeated(team)) {
			controller.teamWin();
		}
	}

	/**
	 * Give turn to next team.
	 */
	public void nextTurn() {
		turn ++;
		teamOnMove ++;
		teamOnMove %= Consts.NUM_TEAMS;
		// Reset moved flag of all pieces in a team
		teamManager.resetTeamMoved(getTeamOnMove());
		controller.switchTurn();
	}

	/**
	 * Sets the turn.
	 * Set turn and team on move to current gameboard and continue gmae
	 *
	 * @param turn the turn count to be set
	 * @param teamOnMove the team number on move
	 */
	public void setTurn(int turn, int teamOnMove) {
		this.turn = turn;
		this.setTeamOnMove(teamOnMove);
		// Reset moved flag of all pieces in a team
		teamManager.resetTeamMoved(teamOnMove);
		controller.switchTurn();
	}

	/**
	 * Check weapon.
	 * Check any weapon in the cell(x, y). Convert a basic entity(Soldier or Spawn) 
	 * to an advanced professional class according to weapon it picked up
	 * @param x the x
	 * @param y the y
	 */
	private void checkWeapon(int x, int y) {
		Weapon weapon = getBoardCell(x, y).getWeapon();

		if(weapon!=null) {
			Entity target = gameBoard[x][y].getEntity();
			if(controller.foundWeapon(weapon.getName())) {
				Entity ne = ProfessionManager.changeProfession(target, weapon.getType());
				//System.out.println(ne.getClass());
				teamManager.setEntityByXY(x, y, ne);
				getBoardCell(x, y).clearWeapon();
				updateBoard();
			}
		}
	}

	/**
	 * Gets the team on move.
	 *
	 * @return the team number on move
	 */
	public int getTeamOnMove() {
		return teamOnMove;
	}

	/**
	 * Sets the team number on move.
	 *
	 * @param i the new team on move
	 */
	public void setTeamOnMove(int i) {
		teamOnMove = i;
	}


	/**
	 * 
	 * Name: createMemento() Parameters: ()
	 * Returns: MementoInterface reference
	 * This function practices factory design pattern to create
	 * a Memento object.
	 * 
	 *
	 * @return the memento interface
	 */

	public MementoInterface createMemento(){
		return new Memento(this.state);
	}

	/**
	 * Restore memento.
	 *
	 * @param memento the memento
	 */
	public void restoreMemento(MementoInterface memento){
		// get a saved state from memento object passed in
		if(memento != null) {
			Memento aMemento = (Memento) memento;
			this.setState(aMemento.getState());
		}
	}

	/**
	 * Sets the current game state.
	 *
	 * @param state the new state
	 */
	public void setState(State state){
		int x,y;

		this.state = state;
		Entity invoker = this.state.invoker;
		x = this.state.x;
		y = this.state.y;
		// Call undo method
		this.state.invoker.undoLastInvoke();
		// Set cloned entity from its previous state to current position
		// For handling convertion between basic unit and advanced unit
		//
		Entity e = teamManager.getEntityByName(this.state.invoker.getName());
		if(invoker.getClass() != e.getClass()) {
			Entity inner = ((ProfessionDecorator)e).getEntity();
			inner.setProfessionName(invoker.getProfessionName());
			inner.setCurrentHP(invoker.getCurrentHP());
			inner.setPos(x, y);
			inner.setMaxHP(invoker.getMaxHP());
			inner.setStrength(invoker.getStrength());
			inner.setAgility(invoker.getAgility());
			inner.setDefense(invoker.getDefense());
			inner.setUpgradable(invoker.isUpgradable());
			inner.setAttackName(invoker.getAttackName());
			inner.setDescription(invoker.getDescription());
			teamManager.setEntityByXY(x, y, ((ProfessionDecorator)e).getEntity());
		}

		setTurn(this.state.turn, this.state.teamOnMove);
		updateBoard();
	}

	/**
	 * Save state.
	 *
	 * @param x the x cooridinate
	 * @param y the y cooridinate
	 * @param entity the entity
	 */
	public void saveState(int x, int y, Entity entity) {
		this.state.x = x;
		this.state.y = y;
		this.state.turn = this.turn;
		this.state.teamOnMove = this.teamOnMove;
		// Prototype pattern:
		// Here we make a good use of prototype pattern
		// We make a clone from a entity in case it changes class after
		// it been moved.
		try {
			Point p = new Point(x, y);
			this.state.invoker = (Entity) entity.clone();
		} catch (IndexOutOfBoundsException e) {
			this.state.invoker = entity;
		}

	}

	/**
	 *
	 * @param type: Command type CommandType.Attack/UPCAST/DOWNCAST
     */

	@Override
	public void update(CommandType type) {
		CommandType[] enumValues = CommandType.values();
		type = enumValues[(type.ordinal()) % enumValues.length];
		controller.setAttack(type.toString());
	}

	/**
	 * The Class Memento.
	 */
	protected class Memento implements MementoInterface {
		
		/** The saved state. */
		private State savedState;
		
		/**
		 * Instantiates a new memento.
		 *
		 * @param state the state object
		 */
		private Memento(State state){
			this.savedState = new State(state.x, state.y, state.turn, state.teamOnMove, state.invoker);
		}

		/**
		 * Gets the state.
		 *
		 * @return the state
		 */
		private State getState(){
			return savedState;
		}
	}
	
	/**
	 * Save game.
	 * Saving entity arraylists, bsize, turn, weapon, gameBoard.
	 */

	public void saveGame(){
		//variables of game data for serialization
		
		ArrayList<Entity> humanTeam = 
				teamManager.getTeam(Consts.HUMAN_TEAM_NUM).getMembers();
		ArrayList<Entity> alienTeam = 
				teamManager.getTeam(Consts.ALIEN_TEAM_NUM).getMembers();
		int bsize = this.BSIZE;
		int currentTurn = this.turn;
		int teamOnMove = getTeamOnMove();
		BoardCell[][] board = gameBoard;
		Weapon[] weapons = this.boardWeapons;
		
		//write objects into single gamedata object
		ArrayList<Object> gameData = new ArrayList<Object>();
		gameData.add(humanTeam);
		gameData.add(alienTeam);
		gameData.add(bsize);
		gameData.add(currentTurn);
		gameData.add(teamOnMove);
		gameData.add(board);
		gameData.add(weapons);
		
		//write gamedata object to file (serializing)
		try {
			controller.showSavingVerbose();
			FileOutputStream fileOut = new FileOutputStream("saveData.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gameData);
			out.close();
			fileOut.close();
	
		} catch(IOException i){
			i.printStackTrace();
		}
	}
	
	/**
	 * Load game.
	 * Loading entity arraylists, bsize, turn, weapon, gameBoard.
	 */
	public void loadGame(){
		//variables of game data for deserialization
		ArrayList<Entity> humanTeam = null; //0 (index of deserialized arraylist)
		ArrayList<Entity> alienTeam = null; //1
		int bsize = 0;						//2
		int currentTurn = 0;				//3
		int teamOnMove = 0;					//4
		BoardCell[][] board = null;			//5
		Weapon[] weapons = null;			//6
		ArrayList<Object> deserialized;		//stores deserialized objects
		
		//read the objects (deserializing)
		try {
			FileInputStream fileIn = new FileInputStream("saveData.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			deserialized = (ArrayList<Object>)in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException i){
			i.printStackTrace();
			return;
		} catch(ClassNotFoundException c){
			c.printStackTrace();
			return;
		}
		
		//Put deserialized data back into respective types
		humanTeam = (ArrayList<Entity>)deserialized.get(TEAM_ONE_ARRAYLIST_OBJECT_INDEX);
		alienTeam = (ArrayList<Entity>)deserialized.get(TEAM_TWO_ARRAYLIST_OBJECT_INDEX);
		bsize = (Integer) deserialized.get(BOARD_SIZE_OBJECT_INDEX);
		currentTurn = (Integer)deserialized.get(TURN_COUNT_OBJECT_INDEX);
		teamOnMove = (Integer)deserialized.get(TEAM_ON_MOVE_OBJECT_INDEX);
		board = (BoardCell[][])deserialized.get(BOARD_CELL_2DARRAY_OBJECT_INDEX);
		weapons = (Weapon[])deserialized.get(WEAPON_ARRAY_OBJECT_INDEX);
		
		Team hTeam = new Team(TeamTypes.Human);
		Team aTeam = new Team(TeamTypes.Alien);
		hTeam.setMembers(humanTeam);
		aTeam.setMembers(alienTeam);
		teamManager.setTeam(hTeam, Consts.HUMAN_TEAM_NUM);
		teamManager.setTeam(aTeam, Consts.ALIEN_TEAM_NUM);
		Consts.BSIZE = bsize;

		gameBoard = board;
		this.boardWeapons = weapons;
		setTurn(currentTurn, teamOnMove);
	}
	
	
}
