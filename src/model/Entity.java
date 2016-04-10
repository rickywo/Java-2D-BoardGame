package model;


public class Entity implements EntityInterface{

	//private static final int INIT_VALUE = 10; // initial value of all attributes
	//private static final String COMMANDOR = "Commandor";
	private String name;
	private int team; // Camp of this unit belongs to
	private Profession profession; // Professional class
	private int strength; // An attribute (strength) for causing damage
	private int agility; // An attribute (agility) for moving on the grid map
	private int currentHP; // HP
	private int maxHP; //max HP
	private int defense; // Defence
	private String attackName; //name of primary attack
	//private Point mPos;
	private boolean moved;
	private Weapon weapon;
	private int xPos;	// x coordinates
	private int yPos; //y coords
	
	
//	public Entity() {
//		name = "";
//		team = 0;
//		profession = null; // Class code of the King
//		strength = INIT_VALUE;
//		agility = INIT_VALUE;
//		maxHP = INIT_VALUE;
//		currentHP = maxHP;
//		defense = INIT_VALUE;
//		//mPos = new Point(0, 0);
//		moved = false;
//	}
//	
//	public Entity(String name,int camp, Profession prof, int str, int agi, 
//			int hp, int def, Point p) {
//		this.name = name;
//		this.team = camp;
//		this.profession = prof;
//		this.strength = str;
//		this.agility = agi;
//		this.maxHP = hp;
//		this.currentHP = this.maxHP;
//		this.defense = def;
//		//mPos = p;
//		this.moved = false;
//	}
	
//	public Entity(String name, int team, Profession prof){
//		this.name = name;
//		this.team = team;
//		this.profession = prof;	//also sets hp,str,agi,def
//		//mPos = new Point(0, 0);
//		moved = false;
//		weapon = null;
//	}
	
	public Entity(String name, int team){
		this.name = name;
		this.team = team;
		//mPos = new Point(0, 0);
		moved = false;
		weapon = null;
	}

	@Override
	public void resetTurn() {
		moved = false;
	}

	@Override
	public int calculateSteps(int steps) {
		return steps;
	}

	@Override
	public void moveTo(Point p) {
		moved = true;
	}

	@Override
	public int attack() {
		return 0;
	}

	@Override
	public void beAttacked(int damage) {
		
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession prof) {
		this.profession = prof;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int hp) {
		this.maxHP = hp;
	}
	
	public int currentHP() {
		return currentHP;
	}

	public void setCurrentHP(int hp) {
		this.currentHP = hp;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public String getAttackName(){
		return attackName;
	}
	
	public void setAttackName(String name){
		this.attackName = name;
	}
	
	public void setPos(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	//remove later
	public void printAllAttributes(){
		System.out.println("Name: " + name);
		System.out.println("Team: " + team);
		System.out.println("MaxHP: " + maxHP);
		System.out.println("CurrentHP: " + currentHP);
		System.out.println("Strength: " + strength);
		System.out.println("Agility: " + agility);
		System.out.println("Defense: " + defense);
		System.out.println("Attack Name: " + attackName);
		System.out.println("Profession: " + profession.getName());
		System.out.println("Description: " + profession.getDescription());
		System.out.println("Weapon: " + weapon);
		System.out.println("Coordinates: " + xPos + ", " + yPos);
		System.out.println();
				
		
	}
	
	

}
